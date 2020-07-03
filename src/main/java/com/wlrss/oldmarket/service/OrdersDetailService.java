package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyOrders;

import java.util.List;

/**
 * 订单明细
 */
public interface OrdersDetailService {

    //查询所有买的商品的订单信息
     List<MyOrders> findAllOrdersDetail(Integer id);

     //根据不同条件查询当前用户所有订单
     List<MyOrders> findAllOptionCondition(int userId,String orderNameOrderNum,boolean flag);

    /**
     * 根据session中的邮箱获取到当前的用户id。用来查他自己的所有邮箱
     * @param email
     * @return
     */
    User findUserIdByEmail(String email);
}
