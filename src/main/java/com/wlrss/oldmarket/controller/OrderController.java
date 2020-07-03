package com.wlrss.oldmarket.controller;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import com.wlrss.oldmarket.service.OrdersDetailService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrdersDetailService ordersDetailService;

    /**
     * 公共方法
     * 获取到当前有用户的id
     * @param session
     * @return
     */
    public int getUserId(HttpSession session){
        //通过注册的邮箱  然后去查当前用户的id
        String email = (String) session.getAttribute("email");
        User user = ordersDetailService.findUserIdByEmail(email);
        return user.getId();
    }

    /**
     * 首页点击我的订单，后台根据当前登录id查询出我的所有订单，返回到页面
     * @return
     */
    @RequestMapping("/findAllOrder")
    public String findAllOrder(String id, Model model,HttpSession session){
        //获取到了用户id
        int userId = getUserId(session);

        List<MyOrders> allOrdersDetail = ordersDetailService.findAllOrdersDetail(userId);
        System.out.println("所有的订单：");
        for (MyOrders orders : allOrdersDetail) {
            System.out.println(orders);
        }
        model.addAttribute("allOrdersDetail",allOrdersDetail);
        return "my-sales";
    }

    /**
     * 根据搜索条件不同查询（我的订单中的不同搜索）
     *
     * @param orderNameOrderNum   根据名称或订单号模糊查询
     * @param model
     * @return
     */
    @RequestMapping("/findAllOption")
    public String findAllOption(String orderNameOrderNum, Model model, HttpSession session){
        //获取到了用户id
        int userId = getUserId(session);

        boolean flag = false;
        List<MyOrders> allOrdersDetail = ordersDetailService.findAllOptionCondition(userId,orderNameOrderNum,flag);
        for (MyOrders orders : allOrdersDetail) {
            System.out.println(orders);
        }
        model.addAttribute("scanfInfo",orderNameOrderNum);
        model.addAttribute("allOrdersDetail",allOrdersDetail);
        return "my-sales";
    }



}
