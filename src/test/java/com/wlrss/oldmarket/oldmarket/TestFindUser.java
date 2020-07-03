package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import com.wlrss.oldmarket.mapper.OrderDetailMapper;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.LoginService;
import com.wlrss.oldmarket.service.OrdersDetailService;
import com.wlrss.oldmarket.service.impl.LoginServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestFindUser {

    @Autowired
    LoginServiceImpl loginService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    OrdersDetailService ordersDetailService;

    @Test
    public void testFindAllUser(){
        List<User> users = loginService.findUserByName("王俊");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testAll() {
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindOrderOption(){
        boolean flag = false;
        List<MyOrders> optionCondition = ordersDetailService.findAllOptionCondition(1,"333",flag);
        for (MyOrders myOrders : optionCondition) {
            System.out.println(myOrders);
        }
    }
}
