package com.wlrss.oldmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    //查询全部用户
    @Override
    public List<User> listAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public List<User> searchUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       if (user.getUsername()!=""&&user.getGender()!=null){
           queryWrapper.eq("userName",user.getUsername()).eq("gender",user.getGender());
       }else if (user.getUsername()==""&&user.getGender()!=null){
           queryWrapper.eq("gender",user.getGender());
       }else if (user.getGender()==null&&user.getUsername()!=""){
           queryWrapper.eq("userName",user.getUsername());
       }else {
           queryWrapper=null;
       }

        return userMapper.selectList(queryWrapper);
    }

    @Override
    public String findUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User user=userMapper.selectOne(queryWrapper);
        String str= JSON.toJSONString(user);
        return str;
    }
}
