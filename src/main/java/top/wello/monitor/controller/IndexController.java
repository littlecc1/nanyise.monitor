package top.wello.monitor.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

/**
 * @auther guocheng
 * @date 2019/11/21 12:48
 */

@Controller
public class IndexController {

    @Value("${mail.smtp_host}")
    private String smtp_host;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.to}")
    private String to;

    //调用后端接口来测试tomcat是否运行
    @Value("${ACTIVEURL_SYS}")
    private String activeUrlSys;

    //调用FTP文件服务器上的文件接口来测试FTP是否运行
    @Value("${ACTIVEURL_FTP}")
    private String activeUrlFtp;

    //JDBC
    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.driverClassName}")
    private String jdbcDriverClassName;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;


    @RequestMapping(value="/index",method= RequestMethod.GET)
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("mail.smtp_host",smtp_host);
        session.setAttribute("mail.username",username);
        session.setAttribute("mail.password",password);
        session.setAttribute("mail.from",from);
        session.setAttribute("mail.to",to);
        session.setAttribute("ACTIVEURL_SYS",activeUrlSys);
        session.setAttribute("ACTIVEURL_FTP",activeUrlFtp);
        session.setAttribute("jdbc.url",jdbcUrl);
        session.setAttribute("jdbc.driverClassName",jdbcDriverClassName);
        session.setAttribute("jdbc.username",jdbcUsername);
        session.setAttribute("jdbc.password",jdbcPassword);
        return "index";
    }
}