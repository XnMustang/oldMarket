package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyUser;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserService {
    //查询全部用户
    List<User> listAllUser();

    //修改用户权限
    void updateUser(User user);

    //条件搜索用户
    List<User> searchUser(User user);

    //根据邮箱查找个人用户信息(用户表和地址表)
    String findMyUserByEmail(String email);

    ////根据邮箱查找个人用户信息(用户表)
    User findUserByEmail(String email);

    //用户修改用户个人信息
    void updateUserData(MyUser myUser);

    //根据id修改密码
    void updatePwById(int id,String password);

    int findUserIdByGoodsId(String goodsid);
}
