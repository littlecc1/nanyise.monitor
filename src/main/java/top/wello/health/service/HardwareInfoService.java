package top.wello.health.service;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.wello.health.util.MailUtil;

import javax.annotation.Resource;
import java.util.Date;

@Component
@EnableScheduling
public class HardwareInfoService {

    @Resource
    NetworkService networkService;

    private Logger logger = LoggerFactory.getLogger(HardwareInfoService.class);

    @Value("${SMTP_HOST}")
    private String smtp_host;

    @Value("${EMAILUSERNAME}")
    private String username;

    @Value("${PASSWORD}")
    private String password;

    @Value("${FROM}")
    private String from;

    @Value("${TO}")
    private String to;

    @Value("${ACTIVEURL}")
    private String activeUrl;



    @Scheduled(cron = "10 * * * * ?")
    public void monitorHardwareInfo(){
        logger.info("定时调用系统硬件信息接口开始:"+new Date());
        String res = networkService.get(activeUrl);
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
            err = "<h3>tomcat异常</h3>";
            MailUtil.send(smtp_host, username, password, from, to, "系统异常邮件" , err);
        }
        logger.info("定时调用系统硬件信息接口结束:"+new Date());
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
