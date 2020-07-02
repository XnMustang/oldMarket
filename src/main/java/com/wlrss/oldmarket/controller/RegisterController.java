package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.service.impl.RegisterServiceImpl;
import com.wlrss.oldmarket.utils.EmailCheck;
import com.wlrss.oldmarket.utils.MD5Util;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户注册，邮箱验证
 */
@Controller
public class RegisterController {

    @Autowired
    RegisterServiceImpl registerService;

    /**
     * 用户注册
     */
    @RequestMapping(value = "/register")
    public String userRegister(String email , String password, String rePassword, Model model){

        //数据验证
        if(EmailCheck.emailFormat(email)){
            //查询邮箱是否被注册
            boolean b = registerService.emailIsExist(email);
            if (b){
                //前后密码验证
                if(password.equals(rePassword)){
                    //数据验证通过
                    //密码MD5加密
                    String md5 = MD5Util.finishMD5(password);
                    User user = new User();
                    user.setUsername(email.substring(0,5)).setStatus("0").setVip("0").
                            setGoodscount(0).setGoodscountyet(0).setEmail(email).setPassword(md5);
                    //存入数据库
                    registerService.addUser(user);
                    return "redirect:/";
                }else {
                    //两次密码不一致
                    model.addAttribute("msg","两次密码不一致");
                    return "login-register";
                }
            }else{
                model.addAttribute("msg","此邮箱已经被注册");
                return  "login-register";
            }
        }else {
            //邮箱格式不正确
            return "login-register";
        }
    }


    /**
     * 邮箱激活
     * @param email
     * @param model
     * @return
     */
    @RequestMapping(value = "/activation/{email}")
    public String activationMail(@PathVariable("email") String email,Model model){
        //判断邮箱是否存在
        boolean flag = registerService.emailIsExist(email);
        if (!flag){
            //激活
            registerService.activation(email);
            return "redirect:/";
        }else {
            model.addAttribute("msg","邮箱不存在");
            return  "login-register";
        }
    }
}
