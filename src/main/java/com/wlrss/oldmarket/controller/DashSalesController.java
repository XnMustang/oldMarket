package com.wlrss.oldmarket.controller;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.vo.DashSales;
import com.wlrss.oldmarket.service.impl.DashSalesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/7/007 14:26
 */
@Controller
@RequestMapping("/dashSales")
public class DashSalesController {

    @Autowired
    DashSalesServiceImpl dashSalesService;

    @RequestMapping("/findAll")
    @ResponseBody
    public List<DashSales> selectAll() {
        List<DashSales> all = dashSalesService.findAll();

        System.out.println("<><><><><><<><><><><><<"+all);
        return all;
    }

    @RequestMapping("/findAllByDateUpDesc")
    @ResponseBody
    public List<DashSales> selectAllByDateUpDesc() {
        List<DashSales> all = dashSalesService.findAllByDateUpDesc();
        System.out.println("<><><><><><<><><><><><<"+all);
        return all;
    }

    @RequestMapping("/findAllByDateDownDesc")
    @ResponseBody
    public List<DashSales> selectAllByDateDownDesc() {
        List<DashSales> all = dashSalesService.findAllByDateDownDesc();

        System.out.println("<><><><><><<><><><><><<"+all);
        return all;
    }

    @RequestMapping("/findAllByPriceDesc")
    @ResponseBody
    public List<DashSales> selectAllByPriceDesc() {
        List<DashSales> all = dashSalesService.findAllByPriceDesc();

        System.out.println("<><><><><><<><><><><><<"+all);
        return all;
    }

    @RequestMapping("/findAllByPriceAsc")
    @ResponseBody
    public List<DashSales> selectAllByPriceAsc() {
        List<DashSales> all = dashSalesService.findAllByPriceAsc();

        System.out.println("<><><><><><<><><><><><<"+all);
        return all;
    }

    @RequestMapping("/findAllByName")
    @ResponseBody
    public List<DashSales> selectAllByName(String goodsname) {

        List<DashSales> all = dashSalesService.findAllByName(goodsname);

        System.out.println("这是根据关键字查询——+——+——+——+——+——+——++"+all);
        System.out.println("这是显示asd的东西《》《》《》《》《》《《《》《》"+goodsname);
        return all;
    }


}
