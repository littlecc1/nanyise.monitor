package top.wello.health.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import top.wello.health.dao.UserDAO;
import top.wello.health.dao.UserDTO;
import top.wello.health.util.Utility;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
@Lazy
public class UserSessionService {

    private static final String SESSION_PRE = "h:u:s";
    private static Logger logger = LoggerFactory.getLogger(UserSessionService.class);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Resource
    WechatService wechatService;
    @Autowired
    UserDAO userDAO;

    private UserDTO getUserById(long id) {
        logger.info("get user by id " + id);
        UserDTO user = userDAO.getUserById(id);
        return user;
    }

    public UserDTO getUserBySession(String session) {
        if (Utility.isEmpty(session)) {
            return null;
        }
        logger.info("get user by session " + session);
        UserDTO user = userDAO.getUserBySession(session);
        return user;
    }

    private UserDTO addUser(String wechatSession, String wechatOpenId) {
        if (Utility.isEmpty(wechatOpenId) || Utility.isEmpty(wechatOpenId)) {
            return null;
        }
        UserDTO user = new UserDTO();
        user.setWechatSession(wechatSession);
        user.setWechatOpenId(wechatOpenId);
        return addUser(user);
    }

    private UserDTO addUser(UserDTO user) {
        if (Utility.isEmpty(user.getWechatOpenId()) || Utility.isEmpty(user.getWechatSession())) {
            return null;
        }
        user.setId(generateId(user.getWechatOpenId()));
        user.setSession(generateSessionFromWechat(user.getWechatSession()));
        logger.info("add user " + user.getId());
        userDAO.addUser(user);
        return user;
    }

    public void updateUser(UserDTO user) {
        if (user.getId() == 0 || Utility.isEmpty(user.getWechatOpenId()) ) {
            return;
        }
        userDAO.updateUser(user);
    }

    public UserDTO login(String code) {
        if (Utility.isEmpty(code)) {
            return null;
        }
        WechatService.WechatLogin login = wechatService.login(code);
        if (login == null || Utility.isEmpty(login.getOpenid()) || Utility.isEmpty(login.getSession_key())) {
            logger.error("wechar auth error " + wechatService);
            return null;
        }
        String openId = login.getOpenid();
        String wechatSession = login.getSession_key();
        UserDTO user = userDAO.getUserByWechatOpenId(openId);
        if (user == null) {
            logger.info("sign up for user " + openId);
            user = addUser(wechatSession, openId);
            user.newUser = true;
        } else {
            user = updateSession(user, wechatSession);
        }
        return user;
    }

    private UserDTO updateSession(UserDTO user, String wechatSession) {
        if (Utility.isEmpty(wechatSession) || !isValidUser(user)) {
            return null;
        }
        String session = generateSessionFromWechat(wechatSession);
        user.setWechatSession(wechatSession);
        user.setSession(session);
        userDAO.updateUser(user);
        return user;
    }

    private long generateId(String wechatOpenId) {
        if (Utility.isEmpty(wechatOpenId)) {
            throw new IllegalArgumentException("wechatOpenId cannot be null or zero length");
        }
        return Long.valueOf("101" + Math.abs(("wx" + wechatOpenId).hashCode()));
    }

    private String generateSessionFromWechat(String wechatSession) {
        if (Utility.isEmpty(wechatSession)) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        return md5(SESSION_PRE + wechatSession);
    }

    private String md5(String origin) {
        if (Utility.isEmpty(origin)) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(origin.getBytes());
            byte[] hash = md.digest();
            for (byte h : hash) {
                if ((0xff & h) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & h)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & h));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString();
    }


    private static boolean isValidUser(UserDTO user) {
        if (user == null || Utility.isEmpty(user.getWechatSession()) || Utility.isEmpty(user.getWechatOpenId())) {
            return false;
        }
        return String.valueOf(user.getId()).startsWith("101");
    }

    public static boolean isValidBirth(String birth) {
        if (Utility.isEmpty(birth)) {
            return false;
        }
        try {
            simpleDateFormat.parse(birth);
            return true;
        } catch (ParseException e) {
            logger.error(birth + " is not legal birth date");
            return false;
        }
    }

}
