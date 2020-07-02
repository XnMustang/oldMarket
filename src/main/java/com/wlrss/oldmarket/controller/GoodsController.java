package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jamesBond
 * @createTime 2020/7/1/001 19:57
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl service;

    @RequestMapping("/addGood")
    @ResponseBody
    public String addGood(Goods goods) {
        service.insertGoods(goods);
        return "redirect:/dash-items";
    }
}
