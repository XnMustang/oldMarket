package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 测试传参读取数据库是否正常
     */
    @RequestMapping("/login")
    @ResponseBody
    public void login(){
        List<User> users = loginService.findUserByName("张三");
        for (User user : users) {
            System.out.println(user);
        }
    }

}
