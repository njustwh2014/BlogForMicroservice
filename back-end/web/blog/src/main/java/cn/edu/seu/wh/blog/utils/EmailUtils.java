package cn.edu.seu.wh.blog.utils;
import cn.edu.seu.wh.blog.result.ResultInfo;
import org.springframework.stereotype.Service;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailUtils {
    private Properties pp;


    /**
     * 注册发送验证码
     * @param captcha
     * @param email
     */
    public ResultInfo sendCaptcha(String captcha, String email) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.163.com");
        prop.put("isSSL","true");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.port", "465");

        prop.put("mail.smtp.port", "25");//465
        prop.put("mail.user", "wanghuan199604@163.com");//
        prop.put("mail.password", "520whqcmx");//
        this.pp=prop;

        Authenticator authenticator=new Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = pp.getProperty("mail.user");
                String password = pp.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(pp, authenticator);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress form = new InternetAddress(pp.getProperty("mail.user"),"SEU_MSTC_Blog");

            message.setFrom(form);

            InternetAddress to = new InternetAddress(email);

            message.setRecipient(MimeMessage.RecipientType.TO, to);

            message.setSubject("验证码提醒");

            message.setSentDate(new Date());




            MimeMultipart related = new MimeMultipart("related");

            MimeBodyPart content = new MimeBodyPart();


            content.setContent("欢迎注册SEU_MSTC_Blog，这是你的验证码：<span style='font-weight:bold;'>"+captcha+"</span>"+"<br/>若不是本人操作，请忽略本邮件。", "text/html;charset=UTF-8");

            related.addBodyPart(content);
            //related.addBodyPart(resource);

            message.setContent(related);

            Transport.send(message);

        }  catch (Exception e) {
            // TODO Auto-generated catch block
            return ResultInfo.build(500, "发送验证码邮件发生错误！");
        }
        return ResultInfo.ok();
    }



    /**
     * 注册成功后发送邮件
     * @param user
     * @param email
     */
    public void sendEmail(String user,String email){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", "smtp.qq.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.user", "picshow@foxmail.com");
        prop.put("mail.password", "aweqrlbjufmedbfj");//wetsxjprfldrejch
        this.pp=prop;
        Authenticator authenticator=new Authenticator(){

            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = pp.getProperty("mail.user");
                String password = pp.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(pp, authenticator);
        MimeMessage message = new MimeMessage(session);
        try {
            InternetAddress form = new InternetAddress(pp.getProperty("mail.user"),"Picshow");

            message.setFrom(form);

            InternetAddress to = new InternetAddress(email);

            message.setRecipient(MimeMessage.RecipientType.TO, to);

            message.setSubject(user+"，欢迎你加入SEU_MSTC_Blog！");

            message.setSentDate(new Date());


            //message.setContent("<a href='http://1gj7070262.iask.in/chat'>请从这里开始</a>", "text/html;charset=UTF-8");

            MimeMultipart related = new MimeMultipart("related");

            MimeBodyPart content = new MimeBodyPart();
            MimeBodyPart resource = new MimeBodyPart();

            //String filePath=RegisterServiceImpl.class.getResource("/梦想.jpg").getPath();
            //filePath= URLDecoder.decode(filePath, "utf-8");
            //DataSource ds=new FileDataSource(new File(filePath));
            //DataHandler handler = new DataHandler(ds);
            //resource.setDataHandler(handler);
            //resource.setContentID("dream.jpg");

            content.setContent("<img src='cid:dream.jpg' width='100%'/> <a href='http://1gj7070262.iask.in/chat'>Picshow从这里开始</a>", "text/html;charset=UTF-8");

            related.addBodyPart(content);
            related.addBodyPart(resource);

            message.setContent(related);
            Transport.send(message);
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
