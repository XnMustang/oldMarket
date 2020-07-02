package com.wlrss.oldmarket.controller;


import com.wlrss.oldmarket.entity.vo.MyOrders;
import com.wlrss.oldmarket.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrdersDetailService ordersDetailService;

    /**
     * 首页点击我的订单，后台根据当前登录id查询出我的所有订单，返回到页面
     * @return
     */
    @RequestMapping("/findAllOrder")
    public String findAllOrder(String id, Model model){
        List<MyOrders> allOrdersDetail = ordersDetailService.findAllOrdersDetail(1);
        System.out.println("所有的订单：");
        for (MyOrders orders : allOrdersDetail) {
            System.out.println(orders);
        }
        model.addAttribute("allOrdersDetail",allOrdersDetail);
        return "my-sales";
    }

}
