package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.User;

import java.util.List;

public interface LoginService {


    /**
     * 查询用户
     * @param username
     * @return
     */
    List<User> findUserByName(String username);

}
