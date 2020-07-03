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


    /**
     * 根据不同条件查询当前用户所有订单
     * @param userId                查看哪个用户的订单明细
     * @param orderNameOrderNum     根据订单或者商品名称查询订单
     * @param flag                  标志位,动态SQL判断输入的是订单编号还是商品名称
     * @return
     */
     List<MyOrders> findAllOptionCondition(int userId,String orderNameOrderNum,boolean flag);

    /**
     * 根据session中的邮箱获取到当前的用户id。用来查他自己的所有邮箱
     * @param email
     * @return
     */
    int findUserIdByEmail(String email);


    /**
     * 根据订单状态查询
     * @param optionValue
     * @return
     */
    List<MyOrders> findStatus(int userId,String optionValue);
}
