package com.wlrss.oldmarket.controller;

import com.github.pagehelper.IPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlrss.oldmarket.entity.vo.MyNewGoodsVo;
import com.wlrss.oldmarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 导航-->最新商品 页面展示
 */

@Controller
@RequestMapping("/items")
public class ItemsController {

    //在用户不登陆的情况下，查询所有的商品，所以无需获取用户id
    @Autowired
    GoodsService goodsService;

    @RequestMapping("/queryAllGoods")
    public String queryAllGoods(Model model,
                                @RequestParam(required = false,defaultValue = "",value = "queryGoods") String queryGoods,
                                @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                @RequestParam(required = false,defaultValue="4",value="pageSize")Integer pageSize){

        pageNum = 1;
        pageSize = 4;
        PageHelper.startPage(pageNum,pageSize);
        List<MyNewGoodsVo> goodList = goodsService.queryAllGoods(queryGoods);

        System.out.println("分页的结果：");
        for (MyNewGoodsVo good : goodList) {
            System.out.println(good);
        }

        PageInfo<MyNewGoodsVo> pageInfo = new PageInfo<>(goodList);

        model.addAttribute("pageInfo",pageInfo);
        return "items";

    }
}
