package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wlrss.oldmarket.entity.vo.MyNewGoodsVo;
import com.wlrss.oldmarket.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/queryGoods")
    @ResponseBody
    public String queryAllGoods(String keyword,String type,
                                @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                                @RequestParam(required = false,defaultValue="4",value="pageSize")Integer pageSize){

        System.out.println("关键字keyword={"+keyword+"}..类型type={"+type+"}pageNum={"+pageNum+"}pageSize={"+pageSize);

        PageHelper.startPage(pageNum,pageSize);//固定一页显示4条数据
        List<MyNewGoodsVo> goodList = goodsService.queryAllGoods(keyword,type);

        System.out.println("分页的结果goodlist："+goodList);
        PageInfo<MyNewGoodsVo> pageInfo = new PageInfo<>(goodList);
        System.out.println(pageInfo);
        String p=JSON.toJSONString(pageInfo);
        System.out.println("pageInfo<>JSON<>==="+p);
        return p;

    }
}
