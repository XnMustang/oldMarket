package com.wlrss.oldmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/jump")
public class JumpController {

    @RequestMapping("/vip")
    public String vip(){
        return "pricing";
    }


    @RequestMapping("/main")
    public String main(){
        return  "index";
    }

    @RequestMapping("/items")
    public String item(){
        return  "items";
    }

    @RequestMapping("/it/{id}")
    public String itemsDetail(@PathVariable("id") String id) {
        System.out.println("传来的商品id："+id);
        return "redirect:/item-detail.html?"+id;
    }

    @RequestMapping("/blog")
    public String blog(){
        return  "blog";
    }

    @RequestMapping("/about")
    public String about(){
        return  "about";
    }

    @RequestMapping("/dashboard")
    public String dashboard(){
        return  "dashboard";
    }

    @RequestMapping("/messages")
    public String messages(){
        return  "dash-messages";
    }

    @RequestMapping("/reviews")
    public String reviews(){
        return  "dash-reviews";
    }

    @RequestMapping("/dash-items")
    public String dashItems(){
        return  "dash-items";
    }


    @RequestMapping("/dash-sales")
    public String sale(){
        return  "dash-sales";
    }

    @RequestMapping("/dash-profile")
    public String profile(){
        return  "dash-profile";
    }

    @RequestMapping("/dash-accountHistory")
    public String accountHistory(){
        return  "dash-accountHistory";
    }

    @RequestMapping("/dash-addItem")
    public String addItem(){
        return  "dash-addItem";
    }
}
