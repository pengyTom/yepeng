package com.zzh.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zzh.common.ServerResponse;
import com.zzh.dao.UserMapper;
import com.zzh.entity.User;
import com.zzh.service.IUserService;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @Override
    public ServerResponse logion(String username, String password) {
        EntityWrapper<User> ew = new EntityWrapper<User>();
        //查询条件，根据用户名和密码查询出条件
        ew.eq("username",username).eq("password",password);
        User user=new User();
        if (user.selectOne(ew)!=null){
            user.setPassword("******");
            //将用户存进session域中
            return ServerResponse.createBySuccess(user);
        }else{
           return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
    }
}
