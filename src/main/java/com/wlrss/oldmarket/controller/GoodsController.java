package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.service.impl.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author jamesBond
 * @createTime 2020/7/1/001 19:57
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsServiceImpl service;

    @Autowired
    GoodsMapper goodsMapper;

    @RequestMapping("/addGood")
    public String addGood(Model model, @ModelAttribute(value = "goods") Goods goods, BindingResult result) {
        System.out.println("商品名"+goods.getGoodsname());
        System.out.println("价格"+goods.getPrice());
        System.out.println("描述"+goods.getDescribed());
        System.out.println("留言"+goods.getSellmassage());

        if (result.hasErrors()){
            model.addAttribute("msg","出错啦");
        }else {
            model.addAttribute("msg","提交成功");
        }
        return "redirect:dash-items";
    }


}
