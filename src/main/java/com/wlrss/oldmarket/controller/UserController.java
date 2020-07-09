package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyUser;
import com.wlrss.oldmarket.service.impl.UserServiceImpl;
import com.wlrss.oldmarket.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    //后台列出全部用户
    @RequestMapping(value = "getAllUser", produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String listAllUser(){
        String s="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": 1000,\n" +
                "  \"data\":"+JSON.toJSONString(userService.listAllUser())+"}";
        System.out.println("userLIst"+JSON.toJSONString(userService.listAllUser()));
        return s;
    }


    //后台操作用户状态
    @RequestMapping("/updateUser")
    @ResponseBody
    public void updateUser(int id,@RequestBody User user){
        user.setId(id);
        System.out.println("打印的user"+user);
        userService.updateUser(user);

    }


    //后台条件查询用户
    @RequestMapping("/searchUser")
    @ResponseBody
    public String searchUser(@RequestBody User user){
        System.out.println("searchUser..."+user);
        String s="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": 1000,\n" +
                "  \"data\":"+JSON.toJSONString(userService.searchUser(user))+"}";
        System.out.println(s);
        return s;
    }


    //个人资料:根据当前用户邮箱查找用户信息,回显
    @RequestMapping("/showPersonalData")
    @ResponseBody
    public String showPersonalData(HttpSession session){
        String email= (String) session.getAttribute("email");
        System.out.println(email);
        String s=userService.findMyUserByEmail(email);
        return s;
    }

    //对个人资料修改
    @RequestMapping("/updatePersonalData")
    public String updatePersonalData(MyUser myUser){
        System.out.println(myUser);
        userService.updateUserData(myUser);
        return "redirect:/dash-profile.html";
    }

    //
    @RequestMapping("/updatePersonalDataPw")
    public String updatePersonalDataPw(int id,String password2){
       String newPw=MD5Util.finishMD5(password2);
        System.out.println("新密码=="+password2);
        userService.updatePwById(id,newPw);
            return "dash-profile";


    }

}
