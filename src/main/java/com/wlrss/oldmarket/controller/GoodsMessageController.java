package com.wlrss.oldmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试接收用户在商品中的留言信息
 */
@Controller
@RequestMapping("/goodsMessage")
public class GoodsMessageController {

    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(@RequestParam("messageInfo") String messageInfo){

        System.out.println("接收到用户给商品的留言信息：" + messageInfo);

        return messageInfo;
    }

}
