package com.wlrss.oldmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlrss.oldmarket.entity.Goods;
import com.wlrss.oldmarket.entity.vo.GoodsList;
import com.wlrss.oldmarket.service.impl.GoodsListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import qiniu.happydns.util.IP;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Source;
import java.util.List;

/**
 * @author jamesBond
 * @createTime 2020/7/8/008 10:23
 */
@Controller
@RequestMapping("/go")
public class GoodsListController {


    @Autowired
    GoodsListServiceImpl goodsListService;

    @RequestMapping("/list")
    @ResponseBody
    public List<GoodsList> selectAll(Model model, Integer now, Integer size) {
        if (now == null){
            now = 1;
        }
        if (size == null){
            size = 3;
        }
        Page<GoodsList> page = new Page<>(now,size);
        IPage<GoodsList> iPage = goodsListService.findAll(page,null);
        System.out.println("==================================================");
        System.out.println("总条数"+page.getTotal());
        System.out.println("当前页"+page.getCurrent());
        System.out.println("总页码"+page.getPages());
        System.out.println("每页多少条"+page.getSize());
        System.out.println("是否有上一页"+page.hasPrevious());
        System.out.println("是否有下一页"+page.hasNext());
        System.out.println("这是查询到的goods列表————————————————————————————————"+page.getRecords());
        System.out.println("这是ipage的值"+iPage.toString());
        System.out.println("==================================================");
        return page.getRecords();
    }
}
