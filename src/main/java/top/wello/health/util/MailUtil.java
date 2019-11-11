package top.wello.health.util;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class MailUtil {
    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

   /* private static String smtp_host = "smtp.qq.com";
    private static String username = "1501407724@qq.com";
    private static String password = "jbulpltgczrfihdd";

    private static String from = "1501407724@qq.com";
    private static String to = "1361506210@qq.com";
    public static String activeUrl = "";*/

    //Spring 依赖注入
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    //Spring 依赖注入
    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
        this.simpleMailMessage = simpleMailMessage;
    }

    /**
     * 单发
     *
     * @param subject   主题
     * @param content   内容
     */

    public static void send(String smtp_host, String username, String password, String from, String to, String subject, String content) {
/* simpleMailMessage.setTo(recipient);
simpleMailMessage.setSubject(subject);
simpleMailMessage.setText(content);
mailSender.send(simpleMailMessage);*/
//    public static void send(String recipients, String subject, String content) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", smtp_host);
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getInstance(props);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");
            Transport transport = session.getTransport();
            transport.connect(smtp_host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("邮件发送失败...");
        }
    }







    /**
     * 群发
     *
     * @param recipients 收件人
     * @param subject    主题
     * @param content    内容
     */
    public void send(List<String> recipients, String subject, String content) {
        simpleMailMessage.setTo(recipients.toArray(new String[recipients.size()]));
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
}
