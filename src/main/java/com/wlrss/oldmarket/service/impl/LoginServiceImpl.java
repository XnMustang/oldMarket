package com.wlrss.oldmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.LoginService;
import com.wlrss.oldmarket.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    /**
     * 根据姓名查询用户信息，验证登录
     * @param username
     * @return
     */
    @Override
    public List<User> findUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    /**
     * 判断邮箱是否激活 true 激活  flase 未激活
     * @param email
     * @return
     */
    @Override
    public boolean checkStatus(String email) {
        AtomicBoolean f = new AtomicBoolean(false);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("email",email);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(user -> {
            if (user.getStatus().equals("1")){
                //激活
                f.set(true);
            }
            if (user.getStatus().equals("0")){
                //未激活
                //发送邮件
                String subject = "欢迎注册OldMarket！！！";
                String content = "此邮件为OldMarket用户注册激活邮件！请点击下面链接完成激活操作此邮件30分钟有效!" +
                        "http://localhost:8080/activation/"+user.getEmail();
                String to = user.getEmail();
                mailService.sendSimpleMail(to,subject,content);
                f.set(false);
            }
        });
        return f.get();
    }

    /**
     * 判断密码是否输入正确  true正确 flase错误
     * @param md5
     * @return
     */
    @Override
    public boolean checkPwd(String md5,String email) {
        boolean p = false;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("email",email);
        User user = userMapper.selectOne(queryWrapper);
            if (md5.equals(user.getPassword())){
                //密码正确
                p=true;
            }
            if (!md5.equals(user.getPassword())){
                //密码不正确
                p=false;
            }
        return p;
        }


}
