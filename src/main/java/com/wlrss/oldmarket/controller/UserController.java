package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyUser;
import com.wlrss.oldmarket.log.MyLog;
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
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;


    //后台列出全部用户
    @RequestMapping(value = "getAllUser", produces = {"application/text;charset=UTF-8"})
    @ResponseBody
    public String listAllUser(Integer limit,Integer page){
        System.out.println("QWEQWEWQWEW==="+limit+"POIOPOIO=="+page);
        PageHelper.startPage(page,limit);
        List<User> list=userService.listAllUser();
        PageInfo<User> pi=new PageInfo<>(list);
        System.out.println("pageinfo"+pi);
        System.out.println("pi.getPages()="+pi.getTotal());
        String s="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": "+pi.getTotal()+",\n" +
                "  \"data\":"+JSON.toJSONString(list)+"}";
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
        PageHelper.startPage(1,10);
        List<User> list=userService.searchUser(user);
        PageInfo<User> pi=new PageInfo<>(list);
        String s="{\n" +
                "  \"code\": 0,\n" +
                "  \"msg\": \"\",\n" +
                "  \"count\": "+pi.getTotal()+",\n" +
                "  \"data\":"+JSON.toJSONString(list)+"}";
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
    @MyLog("修改了个人资料")
    @RequestMapping("/updatePersonalData")
    public String updatePersonalData(MyUser myUser){
        System.out.println(myUser);
        userService.updateUserData(myUser);
        return "redirect:/dash-profile.html";
    }

    //
    @MyLog("修改了登录密码")
    @RequestMapping("/updatePersonalDataPw")
    public String updatePersonalDataPw(int id,String password2){
       String newPw=MD5Util.finishMD5(password2);
        System.out.println("新密码=="+password2);
        userService.updatePwById(id,newPw);
            return "dash-profile";


    }

}
