package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.log.MyExitLog;
import com.wlrss.oldmarket.log.MyLog;
import com.wlrss.oldmarket.service.LoginService;
import com.wlrss.oldmarket.service.MailService;
import com.wlrss.oldmarket.service.RegisterService;
import com.wlrss.oldmarket.service.UserService;
import com.wlrss.oldmarket.service.impl.LoginServiceImpl;
import com.wlrss.oldmarket.service.impl.RegisterServiceImpl;
import com.wlrss.oldmarket.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.security.provider.MD5;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    RegisterServiceImpl registerService;

    @Autowired
    MailService mailService;

    @Autowired
    UserService userService;

    /**
     * 登录
     * @param email
     * @param password
     * @param model
     * @return
     */
    @MyLog("登录了账户")
    @RequestMapping("/login")
    public String login(String email, String password, Model model, HttpSession session){
        //判断 邮箱 是否存在
        if (!registerService.emailIsExist(email)){
            //存在  验证邮箱激活
            if (loginService.checkStatus(email)){
                //已经激活 验证密码
                String md5 = MD5Util.finishMD5(password);
               if(loginService.checkPwd(md5,email)){
                   //密码正确
                   session.setAttribute("email",email);
                   session.setAttribute("status","1");
                   User user = userService.findUserByEmail(email);
                   session.setAttribute("vip",user.getVip());
                   return "redirect:/";
               }else {
                   //密码错误
                   model.addAttribute("msg","密码错误！");
                   return "login-register";
               }
            }else {
                model.addAttribute("msg","邮箱未激活请去注册邮箱激活后登录");
                return "login-register";
            }
        }else {
            //邮箱不存在
            model.addAttribute("msg","邮箱不存在");
            return "login-register";
        }
    }

    /**
     * 用户登出
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    @MyExitLog("退出了登录")
    public  String logout(HttpSession session){
        session.removeAttribute("email");
        session.removeAttribute("status");
        session.invalidate();
        return "redirect:/";
    }
}
