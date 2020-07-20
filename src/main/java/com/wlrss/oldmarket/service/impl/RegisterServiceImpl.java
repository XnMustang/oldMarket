package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.MailService;
import com.wlrss.oldmarket.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private  UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Override
    public void addUser(User user){
        //发送邮件
        String subject = "欢迎注册OldMarket！！！";
        String content = "此邮件为OldMarket用户注册激活邮件！请点击下面链接完成激活操作此邮件30分钟有效!" +
                "http://localhost:8080/activation/"+user.getEmail();
        String to = user.getEmail();
        mailService.sendSimpleMail(to,subject,content);
        userMapper.insert(user);
    }


    /**
     * 判断邮箱是否重复  不存在true  存在flase
     * @param email
     * @return
     */
    @Override
    public boolean emailIsExist(String email) {
        boolean flag = false;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        List<User> users = userMapper.selectList(queryWrapper);
        if (users.size()==0){
            flag = true;
        }
        return  flag;
    }


    /**
     * 激活邮箱
     */
    public void activation(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> {
            User user1 = userMapper.selectById(user.getId());
            user1.setStatus("1");
            userMapper.updateById(user1);
        });
    }
}
