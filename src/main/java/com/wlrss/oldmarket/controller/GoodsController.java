package com.wlrss.oldmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.mapper.GoodsMapper;
import com.wlrss.oldmarket.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    OrdersDetailService ordersDetailService;

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
            //截取重新赋值
            int i =record.getDescribed().length();
            double v = i - i / 1.15;
            int index = (int) Math.floor(v);
            String add = ".....";
            String substring = record.getDescribed().substring(0,index);
            substring+=substring+add;
            record.setDescribed(substring);

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
        queryWrapper.orderByDesc("date_up");
        goodsIPage = goodsMapper.selectPage(goodsIPage,queryWrapper);
        List<Goods> records = goodsIPage.getRecords();

        model.addAttribute("records",records);
        model.addAttribute("goodsIPage",goodsIPage);
        return "search-results";
    }

    @RequestMapping("/addGoods")
    @ResponseBody
    public String addGoods(String goodsName, BigDecimal goodsPrice, String goodsDescribed, String goodsMessage, HttpSession session){
        System.out.println("接收到的参数：" + goodsName+","+goodsPrice+","+goodsDescribed+","+goodsMessage);

        /**
         * 添加商品所需要的参数：
         *  商品id,名称，价格，描述，img，哪个用户上传的，上传时间，卖家留言信息，状态，数量
         */
        int goodsId = 0;
        String imgName = goodsName + "img";
        String email = (String) session.getAttribute("email");
        int userId = ordersDetailService.findUserIdByEmail(email);
        Date goodsTime = new Date();
        int status = 1;
        int nums = 0;
        String messageGoods = "";

        /**
         * 执行商品添加
         * @param goodsId       商品id
         * @param goodsName     名称
         * @param goodsPrice    价格
         * @param goodsDescribed 描述
         * @param imgName       图片
         * @param userId        谁添加的
         * @param goodsTime     添加时间
         *                      卖家留言
         * @param status        状态
         * @param nums          数量
         * @return
         */
        int addResult = ordersDetailService.addGoods(goodsId,goodsName,goodsPrice,goodsDescribed,imgName,userId,goodsTime,messageGoods,status,nums);
        if(addResult > 0){
            //添加成功，刷新页面
            System.out.println("添加成功！");
        }else {
            //添加失败！
            System.out.println("添加失败！");
        }
        return "ok";
    }

}
