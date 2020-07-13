package com.wlrss.oldmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.log.Log;
import com.wlrss.oldmarket.mapper.LogMapper;
import com.wlrss.oldmarket.service.ILogManager;
import com.wlrss.oldmarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class DBLogManger implements ILogManager {
    @Autowired
    UserService userService;
    @Autowired
    LogMapper logMapper;

    @Override
    public void insertLog(Log log) {
        System.out.println("将日志存入数据库,日志内容如下: " + JSON.toJSONString(log));
        logMapper.insert(log);
    }

    @Override
    public List<Log> listLog() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String email= (String) session.getAttribute("email");
        User user=userService.findUserByEmail(email);
        String username=user.getUsername();
        QueryWrapper<Log> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("createdate");
        queryWrapper.eq("username",username);
//        queryWrapper.groupBy("createday");
        List<Log> logs=logMapper.selectList(queryWrapper);
        System.out.println("查出日志:"+logs);
        return logs;
    }


}
