package com.wlrss.oldmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.annotation.LogEnable;
import com.wlrss.oldmarket.annotation.LogEvent;
import com.wlrss.oldmarket.annotation.LogKey;
import com.wlrss.oldmarket.entity.EventType;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.ModuleType;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyUser;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@LogEnable // 启动日志拦截
@LogEvent(module = ModuleType.USER)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    GoodsMapper goodsMapper;
    //查询全部用户
    @Override
    public List<User> listAllUser() {
        return userMapper.selectList(null);
    }

    @Override
    @LogEvent(event = EventType.UPDATE,desc = "修改了用户资料")
    public void updateUser(@LogKey(keyName = "user") User user) {
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
    public String findMyUserByEmail(String email) {
        List<MyUser> myUser=userMapper.findMyUserByEmail(email);
        String str= JSON.toJSONString(myUser);
        System.out.println("查询myuser"+str);
        return str;
    }

    @Override
    public User findUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email",email);
        User user=userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    public void updateUserData(MyUser myUser) {
        User user=new User();
        user.setId(myUser.getId()).setEmail(myUser.getEmail()).setGender(myUser.getGender()).setGoogle(myUser.getGoogle()).setUsername(myUser.getUsername()).setPhone(myUser.getPhone()).setIntro(myUser.getIntro()).setHeadimg(myUser.getHeadimg()).setQq(myUser.getQq());
        userMapper.updateById(user);

    }

    @Override
    public void updatePwById(int id,String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user=userMapper.selectOne(queryWrapper);
        user.setPassword(password);
        userMapper.updateById(user);
    }

    @Override
    public int findUserIdByGoodsId(String goodsid) {
        QueryWrapper<Goods> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("goodsid",goodsid);
        Goods goods = goodsMapper.selectOne(queryWrapper);
        return goods.getUserid();
    }

}
