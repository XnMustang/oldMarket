package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.entity.vo.MyOrders;

import java.util.List;

/**
 * 订单明细
 */
public interface OrdersDetailService {

    //查询所有买的商品的订单信息
     List<MyOrders> findAllOrdersDetail(Integer id);

}
