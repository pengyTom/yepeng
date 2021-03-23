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
        /*CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpget = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(httpget);*/
  /*      HttpEntity entity = response.getEntity();
        String strResult = EntityUtils.toString(entity);
        System.out.println(strResult);*/
    }
}
