package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.User;

import java.util.List;

public interface LoginService {

    /**
     * 查询用户
     * @param userName
     * @return
     */
    List<User> findUserByName(String userName);


    boolean checkStatus(String email);

    boolean checkPwd(String ma5,String email);
}
