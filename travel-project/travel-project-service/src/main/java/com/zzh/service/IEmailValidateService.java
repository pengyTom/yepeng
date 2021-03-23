package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.entity.EmailValidate;

/**
 *  邮箱验证码:服务类
 */
public interface IEmailValidateService extends IService<EmailValidate> {

    public boolean sendEmail(String toEmailAccount) throws Exception;

}
