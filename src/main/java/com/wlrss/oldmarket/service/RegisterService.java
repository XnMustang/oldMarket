package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.User;

public interface RegisterService {

    /**
     * 增加用户
     * @param user
     */
    void addUser(User user);


    boolean emailIsExist(String email);
}
