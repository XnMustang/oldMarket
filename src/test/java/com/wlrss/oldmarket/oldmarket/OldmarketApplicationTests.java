package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.mapper.UserMapper;
import com.wlrss.oldmarket.service.LoginService;
import com.wlrss.oldmarket.service.OrdersDetailService;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class OldmarketApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    OrdersDetailService ordersDetailService;

    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }

    @Autowired
    UserMapper userMapper;

    @Autowired
    LoginService loginService;

    /**
     * 测试查询所有用户
     */
    @Test
    public void findBoss(){
        List<User> users = userMapper.selectList(null);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void findMyOrders(){
        List<MyOrders> detail = ordersDetailService.findAllOrdersDetail(1);
        for (MyOrders orders : detail) {
            System.out.println(orders);
        }
    }
}
