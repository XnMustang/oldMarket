package com.wlrss.oldmarket.controller;

import com.sun.mail.imap.protocol.ID;
import com.wlrss.oldmarket.mapper.OrderDetailMapper;
import com.wlrss.oldmarket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;

/**
 * @author jamesBond
 * @createTime 2020/7/3/003 19:53
 */
@Controller
@RequestMapping("/pay")
public class PaymentController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;

    /**
     * 会员服务
     *
     * @return
     */
    @RequestMapping("/list")
    public String findEmail(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        int userId = orderDetailMapper.findUserIdByEmail(email);
        model.addAttribute("user",userMapper.selectById(userId));
        System.out.println("-=-=-=-=-=-=-=--=-="+userMapper.selectById(userId));
        return "payment_putong";
    }
    @RequestMapping("/list1")
    public String findEmail1(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        int userId = orderDetailMapper.findUserIdByEmail(email);
        model.addAttribute("user",userMapper.selectById(userId));
        System.out.println("-=-=-=-=-=-=-=--=-="+userMapper.selectById(userId));
        return "payment_gaoji";
    }
    @RequestMapping("/list2")
    public String findEmail2(Model model, HttpSession session) {
        String email = (String) session.getAttribute("email");
        int userId = orderDetailMapper.findUserIdByEmail(email);
        model.addAttribute("user",userMapper.selectById(userId));
        System.out.println("-=-=-=-=-=-=-=--=-="+userMapper.selectById(userId));
        return "payment_zungui";
    }

}
