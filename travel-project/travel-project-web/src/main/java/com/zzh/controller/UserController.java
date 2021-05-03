package com.zzh.controller;


        import com.baomidou.mybatisplus.mapper.EntityWrapper;
        import com.baomidou.mybatisplus.mapper.Wrapper;
        import com.zzh.common.Const;
        import com.zzh.common.ServerResponse;
        import com.zzh.entity.EmailValidate;
        import com.zzh.entity.User;
        import com.zzh.service.IEmailValidateService;
        import com.zzh.service.IUserService;
        import org.apache.commons.lang3.StringUtils;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

        import javax.servlet.http.HttpSession;
        import java.util.Date;

/**
 *  前端控制器
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IEmailValidateService emailValidateService;

    /**
     * 注册用户信息
     * 根据实时生成的验证码进行查询
     * @param user
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public ServerResponse<String> register (User user,String registerCode,HttpSession session){
        System.out.println(user.toString());
        //邮箱验证码，验证用户的邮箱和验证码是否一致
        EntityWrapper<EmailValidate> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("email",user.getEmail())
                .eq("validate_code",registerCode);
        if(emailValidateService.selectCount(entityWrapper)<=0){
            return ServerResponse.createByErrorMessage("注册码错误");
        }
        //校验用户是否存在
        EntityWrapper<User> userEntityWrapper=new EntityWrapper<>();
        Wrapper<User> userWrapper = userEntityWrapper.eq("email", user.getEmail());
        User user1 = userService.selectOne(userWrapper);

        //保存
        user.setCreateTime(new Date());
        //0 超级管理员   1 管理员   10 普通游客
        user.setRole(10);

        if (user1!=null){
            if (user.getEmail().equals(user1.getEmail())){
                return ServerResponse.createByErrorMessage("用户已存在，请登录！");
            }
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

        if (user.insert()){
            //注册得数据存入到Session中用于鉴定是否存在已经登录得标志
            session.setAttribute(Const.CURRENT_USER,user);
            return ServerResponse.createBySuccessMessage("注册成功，点击跳转首页！");
        }else{
            return ServerResponse.createByErrorMessage("服务器瘫痪了，请联系管理员！");
        }
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
        User user = response.getData();
        if (response.isSuccess()){
            //登录成功之后，将用户信息
            //将用户数据存储进入session域中，用于数据共享  response.getData()---得到的是get User(username,password)
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return  response;
    }

    /**
     * 管理员登录
     * @param  username
     * @param  password
     * @param  session
     * @return
     */
    @RequestMapping("/admin/login")
    @ResponseBody
    public ServerResponse<User> adminLogin(String username, String password, HttpSession session){

        System.out.println("用户名："+username);
        System.out.println("用户密码："+password);
        ServerResponse<User> response= userService.logion(username,password);

        if (response.isSuccess()){
            //1超级管理员   10 普通用户
            User user = response.getData();
            if (user!=null){
                if (user.getRole()>=10){
                    return ServerResponse.createByErrorMessage("请登录管理员用户");
                }
            }
            //管理员账户存入到session中
            session.setAttribute(Const.ADMIN_USER,response.getData());
        }
        return  response;
    }

    @RequestMapping("/update")
    @ResponseBody
    public ServerResponse update(User user){
        if (user!=null){
            user.setUpdateTime(new Date());
        }
        return ServerResponse.createByResult(userService.updateById(user));
    }

    /**
     * 用户信息的更新
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/updateView")
    public String updateView(HttpSession session, Model model){
        User user = (User)session.getAttribute(Const.CURRENT_USER);

        model.addAttribute("user",user);
        return "index/user_info";
    }

    /**
     * 退出功能，Session杀死
     * @param session
     * @return
     */
    @RequestMapping("/logOutView")
    public  String logOutView(HttpSession session){
        session.invalidate();
        return "index/index";
    }

    /**
     * 我的消息，功能开发
     * @param session
     * @return
     */
    @RequestMapping("/message")
    public String message(HttpSession session,Model model){

        return "index/message";
    }


    /**
     * 修改密码功能
     * @param session
     * @param newPassword
     * @return
     */
    @RequestMapping("/updatePasswordView")
    @ResponseBody
    public ServerResponse updatePassword(HttpSession session,String newPassword,String oldPassword){
        //获取到对应的用户对象信息
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorMessage("当前用户未登录！");
        }

        if (StringUtils.isEmpty(newPassword)){
            return ServerResponse.createByErrorMessage("确认密码为空！");
        }

        if (StringUtils.isEmpty(oldPassword)){
            return ServerResponse.createByErrorMessage("新密码为空!");
        }

        if (!oldPassword.equals(newPassword)){
            return ServerResponse.createByErrorMessage("两次密码不一致，请重新输入!");
        }

        //开始更新用户的密码信息--验证用户账户邮箱的合法性
        EntityWrapper<User> userEntityWrapper=new EntityWrapper<>();
        //实体条件被包装成一条Sql对象，最终通过sql进行查询数据
        Wrapper<User> user_email = userEntityWrapper.eq("email", user.getEmail());
        User user1 = userService.selectOne(user_email);
        if (user1!=null){
            user1.setPassword(newPassword);
            user1.setUpdateTime(new Date());
            //更新新的密码
            if (userService.updateById(user1)){
                //杀死当前用户进程信息,登录失效
                session.invalidate();
                return ServerResponse.createBySuccessMessage("密码修改成功，请重新进行登录！");
            }
        }

        return ServerResponse.createByErrorMessage("当前登录信息有误，请联系管理员!");
    }

    /**
     * 用户密码更新操作
     * @return
     */
    @RequestMapping("/updatePassword")
    public String passwordUpdate(HttpSession session,Model model){
        //数据先从session中进行获取，数据也需要带入到新的页面中，存在的服务中
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        model.addAttribute("user",user);
        return "index/update_password";
    }

}

