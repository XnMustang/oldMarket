package com.wlrss.oldmarket.service.impl;

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
    public List<MyOrders> findAllOrdersDetail(Integer id) {
        return orderDetailMapper.findAllOrdersDetail(id);
    }
}
