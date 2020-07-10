package com.wlrss.oldmarket.aop;

import com.wlrss.oldmarket.service.ILogManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
//    @Autowired
//    private LogInfoGeneration logInfoGeneration;

    @Autowired
    private ILogManager logManager;

    @Pointcut("execution(com.wlrss.oldmarket.service.impl.UserServiceImpl")
    public void managerLogPoint() {
    }

//    @Around("managerLogPoint()")
//    public Object aroundManagerLogPoint(ProceedingJoinPoint jp) throws Throwable {
//    }
}
