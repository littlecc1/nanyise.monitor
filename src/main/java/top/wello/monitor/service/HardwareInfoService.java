package top.wello.monitor.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.wello.monitor.util.MailUtil;

import javax.annotation.Resource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

@Component
@EnableScheduling
public class HardwareInfoService {

    @Resource
    NetworkService networkService;

    private Logger logger = LoggerFactory.getLogger(HardwareInfoService.class);

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


    @Scheduled(cron = "0 0/10 * * * ?")
    public void monitorHardwareInfo(){
        logger.info("定时调用系统硬件信息接口开始:"+new Date());
        String res = networkService.get(activeUrlSys);
        String err = "";
        //        JSONStr = JSONStr.substring(2,JSONStr.length()-2);
        if (res != null) {
            try {
                GetMsg getMsg = new Gson().fromJson(res,GetMsg.class);
                if(getMsg.msg.equals("系统运行异常")){
                    err = "<h3>系统硬件异常</h3>";
                    MailUtil.send(smtp_host, username, password, from, to, "系统异常邮件" , err);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            err = "<h3>tomcat服务异常</h3>";
            MailUtil.send(smtp_host, username, password, from, to, "系统异常邮件" , err);
        }
        logger.info("定时调用系统硬件信息接口结束:"+new Date());
    }

    @Scheduled(cron = "0 0/10 * * * ?")
//    @Scheduled(cron = "10 * * * * ?")
    public void monitorFtpInfo(){
        logger.info("定时调用FTP接口开始:"+new Date());
        String res = networkService.getFtp(activeUrlFtp);
        String err = "";
        //        JSONStr = JSONStr.substring(2,JSONStr.length()-2);
        if (res != null) {
            try {
                if(!res.equals("200")){
                    err = "<h3>FTP服务器上无此文件</h3>";
                    MailUtil.send(smtp_host, username, password, from, to, "系统异常邮件" , err);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            err = "<h3>FTP服务异常</h3>";
            MailUtil.send(smtp_host, username, password, from, to, "系统异常邮件" , err);
        }
        logger.info("定时调用FTP接口结束:"+new Date());
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void jdbcall() throws ClassNotFoundException {
        logger.info("定时调用mysql接口开始:"+new Date());
        Class.forName(jdbcDriverClassName);//加载驱动类
        String conn = null;
        String err = "";
        try {
            conn = String.valueOf(DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(conn == null){
            err = "<h3>mysql服务异常</h3>";
            MailUtil.send(smtp_host, username, password, from, to, "系统异常邮件" , err);
        }
        logger.info("定时调用mysql接口结束:"+new Date());

    }


    private static class GetMsg{
        private int status;
        private String msg;
        private String data;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

}
