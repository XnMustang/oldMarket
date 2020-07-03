package com.wlrss.oldmarket.controller;
import com.wlrss.oldmarket.entity.vo.MyOrders;
import com.wlrss.oldmarket.service.OrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        System.out.println("获取到当前用户的邮箱：" + email);
        int userId = ordersDetailService.findUserIdByEmail(email);
        System.out.println("获取到当前用户的id:" + userId);
        return userId;
    }

    /**
     * 首页点击我的订单，后台根据当前登录id查询出我的所有订单，返回到页面
     * @return
     */
    @RequestMapping("/findAllOrder")
    public String findAllOrder(Model model,HttpSession session){
        //获取到了用户id
        int userId = getUserId(session);

        List<MyOrders> allOrdersDetail = ordersDetailService.findAllOrdersDetail(userId);
        System.out.println("查询"+userId+"所有的订单：");
        for (MyOrders orders : allOrdersDetail) {
            System.out.println(orders);
        }
        model.addAttribute("allOrdersDetail",allOrdersDetail);
        return "my-sales";
    }


    /**
     * 根据搜索条件不同查询（我的订单中的不同搜索）
     * @param orderNameOrderNum         根据名称或订单号模糊查询
     * @param model                     返回数据
     * @param session                   通过邮箱获取当前用户的id
     * @return                          把数据返回到视图
     */
    @RequestMapping("/findAllOption")
    public String findAllOption(String orderNameOrderNum, Model model, HttpSession session){
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

    /**
     * 根据状态查询该用户订单
     * @param optionValue      状态标识符（数字类型）
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/findStatus/{optionValue}")
    public String findStatus(@PathVariable("optionValue") String optionValue,HttpSession session,Model model){
        int userId = getUserId(session);

        List<MyOrders> allOrdersDetail = ordersDetailService.findStatus(userId,optionValue);
        System.out.println("根据状态查询出的订单：");
        for (MyOrders orders : allOrdersDetail) {
            System.out.println(orders);
        }
        model.addAttribute("optionValue",optionValue);
        model.addAttribute("allOrdersDetail",allOrdersDetail);
        return "my-sales::div1";
    }

}
