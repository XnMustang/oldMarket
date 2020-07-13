package com.wlrss.oldmarket.service;

import com.wlrss.oldmarket.log.Log;

import java.util.List;

/**
 * 定义日志处理的接口类ILogManager
 * 我们可以将日志存入数据库，
 * 也可以将日志发送到开中间件，如果redis, mq等等。
 * 每一种日志处理类都是此接口的实现类
 */
public interface ILogManager {
    /**
     * 日志处理模块
     * @param param
     */
    void insertLog(Log param);

    List<Log> listLog();
}
