package com.wlrss.oldmarket.service.impl;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.mapper.OrderDetailMapper;
import com.wlrss.oldmarket.service.OrdersDetailService;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<MyOrders> findAllOrdersDetail(Integer userId) {
        return orderDetailMapper.findAllOrdersDetail(userId);
    }

    @Override
    public List<MyOrders> findAllOptionCondition(int userId,String orderNameOrderNum,boolean flag) {
        for(int i = orderNameOrderNum.length();--i>= 0;){
            int chr = orderNameOrderNum.charAt(i);
            if(chr < 48 || chr > 57) {
                flag = false;   //字符
            }else {
                flag = true;    //数字
            }
        }
        System.out.println("false字符，true数字："+flag);
        return orderDetailMapper.findAllOptionCondition(userId,orderNameOrderNum,flag);
    }

    @Override
    public User findUserIdByEmail(String email) {
        return orderDetailMapper.findUserIdByEmail(email);
    }
}
