package com.wlrss.oldmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsMapper goodsMapper;

    static Integer flag = 1;

    @RequestMapping("/findAll")
    public String selectByPage(@RequestParam(value = "queryGoods",required = false) String queryGoods,@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum, Model model) {

        IPage<Goods> goodsIPage = new Page<>(pageNum, 2);
        QueryWrapper<Goods> queryWrapper =new QueryWrapper<>();

        System.out.println("查询商品名："+queryGoods);
        System.out.println("第几页："+pageNum);
        if(queryGoods != null){
            queryWrapper.like("goodsname",queryGoods);
            goodsIPage = goodsMapper.selectPage(goodsIPage, queryWrapper);
        }else {
            goodsIPage = goodsMapper.selectPage(goodsIPage, null);
        }

        System.out.println("分页查询结果：");
        List<Goods> records = goodsIPage.getRecords();
        for (Goods record : records) {
            System.out.println(record);
        }

        System.out.println("共" + goodsIPage.getTotal() + "条记录,当前" + goodsIPage.getCurrent() + ",每页：" + goodsIPage.getSize() + "条");
        model.addAttribute("records",records);
        model.addAttribute("goodsIPage",goodsIPage);
        model.addAttribute("queryGoods",queryGoods);
        return "search-results";
    }

    @RequestMapping("/priceSort")
    public String priceSort(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,Model model){
        System.out.println("执行了价格排序！");
        QueryWrapper<Goods> queryWrapper =new QueryWrapper<>();

        IPage<Goods> goodsIPage = new Page<>(pageNum, 2);
        if (flag == 1) {
            queryWrapper.orderByDesc("price");
            goodsIPage = goodsMapper.selectPage(goodsIPage, queryWrapper);
            flag = 0;
        } else {
            queryWrapper.orderByAsc("price");
            goodsIPage = goodsMapper.selectPage(goodsIPage, queryWrapper);
            flag = 1;
        }

        System.out.println("分页查询结果：");
        List<Goods> records = goodsIPage.getRecords();
        for (Goods record : records) {
            System.out.println(record);
        }
        model.addAttribute("records",records);
        model.addAttribute("goodsIPage",goodsIPage);
        return "search-results";
    }

    @RequestMapping("/timeQuery")
    public String timeQuery(@RequestParam(value = "pageNum",required = false,defaultValue = "1") Integer pageNum,Model model){
        IPage<Goods> goodsIPage = new Page<>(pageNum,2);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("date");
        goodsIPage = goodsMapper.selectPage(goodsIPage,queryWrapper);
        List<Goods> records = goodsIPage.getRecords();

        model.addAttribute("records",records);
        model.addAttribute("goodsIPage",goodsIPage);
        return "search-results";
    }

}
