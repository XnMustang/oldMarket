package com.wlrss.oldmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.EventType;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyUser;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
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
    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public List<User> searchUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
       if (user.getUsername()!=""&&user.getGender()!=null){
          // queryWrapper.eq("userName",user.getUsername()).eq("gender",user.getGender());
           queryWrapper.like("userName",user.getUsername()).like("gender",user.getGender());
       }else if (user.getUsername()==""&&user.getGender()!=null){
           //queryWrapper.eq("gender",user.getGender());
           queryWrapper.like("gender",user.getGender());
       }else if (user.getGender()==null&&user.getUsername()!=""){
          // queryWrapper.eq("userName",user.getUsername());
           queryWrapper.like("userName",user.getUsername());
       }else {
           queryWrapper=null;
       }

        return userMapper.selectList(queryWrapper);
    }

    @Override
    public String findMyUserByEmail(String email) {
        List<MyUser> myUser=userMapper.findMyUserByEmail(email);
        String str= JSON.toJSONString(myUser);
        System.out.println("两表查询myuser"+str);
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

       // 插入log

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
