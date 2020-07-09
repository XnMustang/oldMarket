package com.wlrss.oldmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author jamesBond
 * @createTime 2020/7/9/009 20:03
 */
@Controller
public class Index {

    @GetMapping("/")
    public String index() {
        return "item-detail";
    }

}
