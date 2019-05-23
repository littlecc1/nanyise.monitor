package top.wello.health.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.wello.health.util.Utility;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
public class WechatService {

    private Logger logger = LoggerFactory.getLogger(BaiduService.class);
    private HashMap<String, String> params;

    @Value("${constant.health.wechat.appid}")
    private String wechatKey;

    @Value("${constant.health.wechat.secret}")
    private String wechatSec;

    @Value("${constant.health.wechat.login_url}")
    private String loginUrl;

    @Resource
    NetworkService networkService;

    public WechatLogin login(String code) {
        if (Utility.isEmpty(code)) {
            logger.error("null wechat code");
            return null;
        }
        if (params == null) {
            params = new HashMap<>(4);
            params.put("appid", wechatKey);
            params.put("secret", wechatSec);
            params.put("grant_type", "authorization_code");
        }
        params.put("js_code", code);
        WechatLogin ret = networkService.get(loginUrl, params, WechatLogin.class);
        return ret;
    }

    public static class WechatLogin {

        /**
         * session_key : QUo7rWUWzewtbG95uq4SLw==
         * openid : oT5n30JSFihI80EddceBN05i-cs4
         */

        private String session_key;
        private String openid;
        private String errcode;
        private String errmsg;

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        @Override
        public String toString() {
            return "wechat openId " + openid + " session: " + session_key + " error: " + errcode + " " + errmsg;
        }
    }
}
