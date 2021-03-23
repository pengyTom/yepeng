package com.zzh.test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件进行测试功能数据
 */
public class SendmailUtil {


    //邮件服务器主机名
    // QQ邮箱的 SMTP 服务器地址为: smtp.qq.com
    private static String myEmailSMTPHost = "smtp.qq.com";
    //发件人邮箱，qq邮箱的后缀信息
    private static String myEmailAccount = "@qq.com";
    //发件人邮箱密码（授权码）
    //在开启SMTP服务时会获取到一个授权码，存在QQ邮箱之中设置，开启对应授权
    private static String myEmailPassword = "";
    //发送邮件端口
    private static String emialPort="587";

	public static void main(String[] args) {
		try {
			sendMail("1226282544@qq.com", "叶鹏","邮件来了");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


        public static void sendMail(String toEmailAddress, String emailTitle, String emailContent) throws Exception{
            // 创建Properties 类用于记录邮箱的一些属性
            final Properties props = new Properties();
            // 表示SMTP发送邮件，必须进行身份验证
            props.put("mail.smtp.auth", "true");
            //此处填写SMTP服务器
            props.put("mail.smtp.host", myEmailSMTPHost);
            //端口号，QQ邮箱给出了两个端口，465或587，这里使用587
            props.put("mail.smtp.port", emialPort);
            // 此处填写账号
            props.put("mail.user", myEmailAccount);
            // 此处的密码16位STMP口令 ： wqojjytuhblsgchb
            props.put("mail.password", myEmailPassword);

            // 构建授权信息，用于进行SMTP进行身份验证
            Authenticator authenticator = new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    // 用户名、密码
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    //stmp实现的授权的逻辑系统
                    return new PasswordAuthentication(userName, password);
                }
            };

            // 使用环境属性和授权信息，创建邮件会话
            Session mailSession = Session.getInstance(props, authenticator);
            // 创建邮件消息
            MimeMessage message = new MimeMessage(mailSession);
            // 设置发件人
            InternetAddress form = new InternetAddress(
                    props.getProperty("mail.user"));
            message.setFrom(form);

            // 设置收件人的邮箱
            InternetAddress to = new InternetAddress(toEmailAddress);
            message.setRecipient(Message.RecipientType.TO, to);

            // 设置邮件标题
            message.setSubject(emailTitle);

            // 设置邮件内容的内容体
            message.setContent(emailContent, "text/html;charset=UTF-8");

            // 发送邮件
            Transport.send(message);
        
	}
}
