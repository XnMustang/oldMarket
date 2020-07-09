package com.wlrss.oldmarket.service.impl;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.LogAdmModel;
import com.wlrss.oldmarket.service.ILogManager;
import org.springframework.stereotype.Service;

@Service
public class DBLogManger implements ILogManager {
    /**
     * 日志处理模块
     *
     * @param paramLogAdmBean
     */
    @Override
    public void dealLog(LogAdmModel paramLogAdmBean) {
        System.out.println("将日志存入数据库,日志内容如下: " + JSON.toJSONString(paramLogAdmBean));

    }
}
