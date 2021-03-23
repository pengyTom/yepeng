package com.zzh.service;

import com.baomidou.mybatisplus.service.IService;
import com.zzh.common.ServerResponse;
import com.zzh.entity.User;

/**
 *  服务类
 */
public interface IUserService extends IService<User> {


    public ServerResponse logion(String username, String password);//登录
}
