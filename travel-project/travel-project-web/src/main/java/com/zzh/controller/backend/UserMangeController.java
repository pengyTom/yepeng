package com.zzh.controller.backend;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zzh.common.Const;
import com.zzh.common.ServerResponse;
import com.zzh.entity.User;
import com.zzh.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

/**
 *  前端控制器
 */
@Controller
@RequestMapping("/manager/user")
public class UserMangeController {


    @Autowired
    private IUserService userService;


    /**
     * 用户列表
     * @param keyword
     * @param current
     * @param size
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public ServerResponse list(String keyword, @RequestParam(value="current",defaultValue="1") int current, @RequestParam(value="size",defaultValue="10") int size){
        System.out.println(keyword);
        EntityWrapper<User> userEntityWrapper=new EntityWrapper<User>();
        userEntityWrapper.like("username",keyword).or().like("email",keyword).or().like("phone",keyword);
        return ServerResponse.createBySuccess(userService.selectPage(new Page<User>(current,size),userEntityWrapper));
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/count")
    public int getCountPage(){
        return userService.selectCount(null);
    }

    /**
     * 批量删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public  ServerResponse delete(String[] id){
        return ServerResponse.createByResult(userService.deleteBatchIds(Arrays.asList(id)));
    }

    /**
     * 用户登录
     * @param  username
     * @param  password
     * @param  session
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session){

        ServerResponse<User> response= userService.logion(username,password);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return  response;
    }

    /**
     * 更新
     * @param user
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ServerResponse update(User user){
        user.setUpdateTime(new Date());
        return ServerResponse.createByResult(user.updateById());

    }

    @RequestMapping("/updateView/{id}")
    public String updateView(@PathVariable String id, Model model){
        User user=userService.selectById(id);
        model.addAttribute(user);
        return "backend/user_update";
    }

    @RequestMapping("/loginView")
    public String loginView(){
        return "backend/login";
    }

    /**
     * 新增会员用户  游客或者管理员
     * @param user
     * @return
     */
    @RequestMapping("/saveView")
    @ResponseBody
    public ServerResponse save(User user){

        if (user!=null){
            user.setCreateTime(new Date());
            //正则用户名长度大于3
            if (user.getUsername().length()<3){
                return ServerResponse.createByErrorMessage("用户名长度小于3！");
            }

            //正则密码
            if (user.getPassword().length()<6){
                return ServerResponse.createByErrorMessage("密码小于6位不安全！");
            }

            //正则邮箱
            String reg_email="\\w+(\\w|[.]\\w+)+@\\w+([.]\\w+){1,3}";
            if (!user.getEmail().matches(reg_email)){
                return ServerResponse.createByErrorMessage("邮箱格式不对！");
            }
            //正则手机号
            String reg_phone="(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-8]|18[0-9]|19[0-9])\\d{8}";
            if (!user.getPhone().matches(reg_phone)){
                return ServerResponse.createByErrorMessage("手机号格式不对！");
            }

            //验证用户是否存在数据库中
            EntityWrapper<User> userEntityWrapper=new EntityWrapper<User>();
            Wrapper<User> user_email = userEntityWrapper.eq("email", user.getEmail());
            User user1 = userService.selectOne(user_email);
            if (user1!=null){
                return   ServerResponse.createByErrorMessage("当前用户已存在，请检查！");
            }


            boolean insert = user.insert();
            if (insert){
                return  ServerResponse.createBySuccess("添加用户成功！");
            }else {
                return   ServerResponse.createByErrorMessage("添加用户失败！");
            }
        }
        return   ServerResponse.createByErrorMessage("用户信息不正确！");

    }

    @RequestMapping("/addUserView")
    public String addUser(){
        return "backend/user_detail";
    }

}

