package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.log.Log;
import com.wlrss.oldmarket.service.ILogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    ILogManager iLogManager;

    @RequestMapping("/listLog")
    @ResponseBody
    public List<Log> listAllLog(){
       return iLogManager.listLog();

    }
}
