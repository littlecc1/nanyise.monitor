package top.wello.health.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.wello.health.Request.*;
import top.wello.health.dao.UserDTO;
import top.wello.health.service.*;
import top.wello.health.session.SessionHolder;
import top.wello.health.util.Utility;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/health")
@Lazy
public class HealthController {

    Logger logger = LoggerFactory.getLogger(HealthController.class);

    @Resource
    BaiduService baiduService;

    @Resource
    UserSessionService userSessionService;

    @Resource
    FoodSuggestService foodSuggestService;

    @Resource
    WXRunService wxRunService;

    @Resource
    ConfigService configService;

    @RequestMapping("/baidutoken")
    public RestResult baiduToken() {
        return RestResult.success(baiduService.getBaiduToken());
    }

    @RequestMapping("/login")
    public RestResult login(String code) {
        if (Utility.isEmpty(code)) {
            return RestResult.fail(RestResult.ERROR_LOGIN_NULL_CODE, "Null Code");
        }
        UserDTO user = userSessionService.login(code);
        if (user != null) {
            user.setWechatSession(null);
            UserResult result = new UserResult(user, user.newUser);
            return RestResult.success(result);
        }
        return RestResult.fail(RestResult.ERROR_LOGIN_FAILED, "login failed");
    }

    @RequestMapping("/update")
    public RestResult update(UpdateUserRequest request) {
        UserDTO user = SessionHolder.getUser();
        if (user == null) {
            return RestResult.fail(RestResult.ERROR_LOGIN_FAILED, "login failed");
        }
        boolean changed = false;
        if (request.getSex() > 0) {
            user.setWechatGender(request.getSex());
            changed = true;
        }
        if (request.getHeight() > 0) {
            user.setHeight(request.getHeight());
            changed = true;
        }
        if (request.getWeight() > 0) {
            user.setWeight(request.getWeight());
            changed = true;
        }
        if (request.getSport() > 0) {
            user.setSport(request.getSport());
            changed = true;
        }
        if (UserSessionService.isValidBirth(request.getBirth())) {
            user.setBirth(request.getBirth());
            changed = true;
        }
        if (request.getHeatDay() > 0) {
            user.setHeatDay(request.getHeatDay());
            changed = true;
        }
        if (changed) {
            userSessionService.updateUser(user);
        }
        UserResult result = new UserResult(user);
        return RestResult.success(result);
    }

    @RequestMapping("/suggestMeal")
    public RestResult suggestMeal(SuggestRequest request) {
        if (request.getHeat() != 0) {
            HashMap<String, List<FoodSuggestResult>> map = foodSuggestService.suggest(request);
            if (map != null) {
                return RestResult.success(map);
            }
        }
        return RestResult.fail(RestResult.ERROR_SUGGEST_ERROR, "ERROR_SUGGEST_ERROR");
    }

    @RequestMapping(value = "/werun", method = RequestMethod.POST)
    public RestResult werun(WXRunRequest request) {
        if (Utility.isEmpty(request.getEncryptedData()) || Utility.isEmpty(request.getIv())) {
            return RestResult.fail(RestResult.ERROR_INVALID_PARAM, "ERROR_INVALID_PARAM");
        }
        int step = wxRunService.getTodayStep(request);
        if (step != -1) {
            return RestResult.success(step);
        }
        return RestResult.fail(RestResult.ERROR_WXRUN_ERROR, "ERROR_WXRUN_ERROR");
    }

    @RequestMapping("/test")
    public RestResult test(String s) {
        HashMap<String, List<FoodSuggestResult>> map = foodSuggestService.suggest(3000);
        if (map == null) {
            return RestResult.fail(RestResult.ERROR_SUGGEST_ERROR, "ERROR_SUGGEST_ERROR");
        }
        return RestResult.success(map);
    }

//    @RequestMapping("/sport")
//    public RestResult sport(String s) {
//        return RestResult.success("wonderful test");
//    }

    @RequestMapping("/config")
    public RestResult getConfig(BaseRequest request) {
        String ret = configService.getConfig();
        return RestResult.success(ret);
    }

}
