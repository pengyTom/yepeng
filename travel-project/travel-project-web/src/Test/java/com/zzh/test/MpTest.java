package com.zzh.test;

import com.zzh.service.IEmailValidateService;
import com.zzh.service.impl.EmailValidateServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 测试程序执行
 */
public class MpTest {

    public static  void main(String[] args){
        //创建一个spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//        UserMapper userMapper = applicationContext.getBean("userMapper",UserMapper.class);
        IEmailValidateService emailValidateService=applicationContext.getBean("emailValidateServiceImpl", EmailValidateServiceImpl.class);
        try {
            emailValidateService.sendEmail("1226282544@qq.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试通过http协议获取对应的组件实体信息数据
     * @throws IOException
     */
    @Test
    public  void test1() throws IOException {
        String reg_phone="^((13[0-9])|(14[0-9])|(15([0-9]))|(16)(0-9)|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$";
        System.out.println("17879525966".matches(reg_phone));

        String reg_email="^\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
        System.out.println("1226282544@qq.com".matches(reg_email));
    }
}
