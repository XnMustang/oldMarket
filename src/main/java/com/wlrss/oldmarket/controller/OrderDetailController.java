package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.vo.OrderDateilUserVo;
import com.wlrss.oldmarket.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    OrdersDetailService ordersDetailService;

    @RequestMapping("/queryOrderDetail/{goodId}")
    @ResponseBody
    public String queryOrderDetail(@PathVariable("goodId") Integer goodId){

        System.out.println("接收到的商品id：" + goodId);

        List<OrderDateilUserVo> orderDetail = ordersDetailService.queryOrderDetail(goodId);

        System.out.println("查询出的商品和用户信息：");
        for (OrderDateilUserVo goods : orderDetail) {
            System.out.println(goods);
        }
        String s = JSON.toJSONString(orderDetail);

        System.out.println("转为json字符串："+s);

        return s;
    }

}
