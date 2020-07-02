package com.wlrss.oldmarket.service.impl;

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
}
