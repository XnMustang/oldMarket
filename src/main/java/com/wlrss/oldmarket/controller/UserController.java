package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

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
    @RequestMapping("/updateUser")
    @ResponseBody
    public String updateUser(int id,@RequestBody User user){
        user.setId(id);
        System.out.println("打印的user"+user);
        userService.updateUser(user);
        return "usertable";
    }

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

}
