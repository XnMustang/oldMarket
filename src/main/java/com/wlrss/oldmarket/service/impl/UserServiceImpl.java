package com.wlrss.oldmarket.service.impl;

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
       if (user.getUserName()!=""&&user.getGender()!=null){
           queryWrapper.eq("userName",user.getUserName()).eq("gender",user.getGender());
       }else if (user.getUserName()==""&&user.getGender()!=null){
           queryWrapper.eq("gender",user.getGender());
       }else if (user.getGender()==null&&user.getUserName()!=""){
           queryWrapper.eq("userName",user.getUserName());
       }else {
           queryWrapper=null;
       }

        return userMapper.selectList(queryWrapper);
    }
}
