package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.User;

import java.util.List;

public interface UserService {
    //查询全部用户
    List<User> listAllUser();

    //修改用户权限
    void updateUser(User user);

    //条件搜索用户
    List<User> searchUser(User user);

    //根据邮箱查找个人用户信息
    String findUserByEmail(String email);
}
