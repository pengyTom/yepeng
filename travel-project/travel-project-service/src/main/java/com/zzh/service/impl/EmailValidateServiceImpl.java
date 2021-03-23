package com.zzh.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzh.dao.EmailValidateMapper;
import com.zzh.entity.EmailValidate;
import com.zzh.service.IEmailValidateService;
import com.zzh.util.SendEmailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *  服务实现类
 */
@Service
public class EmailValidateServiceImpl extends ServiceImpl<EmailValidateMapper, EmailValidate> implements IEmailValidateService {

    @Value("${email_SMTPHost}")
    private  String myEmailSMTPHost;
    @Value("${email_Account}")
    private  String myEmailAccount;
    @Value("${email_Password}")
    private  String myEmailPassword ;
    @Value("${email_Port}")
    private  String emialPort;

    /**
     * 保存验证码   当执行邮箱验证码的时候，会在验证码的对应的表中生成对应的四位验证码数据
     * @param toEmailAccount
     * @return
     * @throws Exception
     */
    @Override
    public boolean sendEmail(String toEmailAccount) throws Exception {
        //发送邮件
        SendEmailUtil.setContext(myEmailSMTPHost,myEmailAccount,myEmailPassword,emialPort);
        String registerCode=registerCode();
        String title="注册码为："+registerCode;
        String content="注册码为："+registerCode+",请注意查收";
        SendEmailUtil.sendMail(toEmailAccount,title,content);
        EmailValidate emailValidate=new EmailValidate();
        emailValidate.setEmail(toEmailAccount);
        emailValidate.setValidateCode(registerCode);
        emailValidate.setCreateTime(new Date());
        return emailValidate.insert();
    }


    //生成唯一的注册码
    public  String registerCode(){
        String numberString="";
        //四位位随机数
        int i=(int)(Math.random()*10);//个位
        int j=(int)(Math.random()*100);//十位
        int k=(int)(Math.random()*1000);//百位
        int l=(int)(Math.random()*10000);//千位
        //最高位不为0
        while (l==0) {
            k=(int)(Math.random()*10000);
        }
        numberString=""+(i+j+k+l)+"";
        return numberString;
    }

    /**
     * 测试生成注册激活码
     * @param args
     */
    public static void main(String[] args) {

        EmailValidateServiceImpl validateService = new EmailValidateServiceImpl();
        System.out.println(validateService.registerCode());

    }

}
