package com.wlrss.oldmarket.log;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.service.ILogManager;
import com.wlrss.oldmarket.service.UserService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class MyExitLogAdvice {
    @Autowired
    UserService userService;
    @Autowired
    ILogManager iLogManager;

    private Logger logger= Logger.getLogger(MyExitLog.class);

    @Pointcut("@annotation(com.wlrss.oldmarket.log.MyExitLog)")
    public void myPointcut(){

    }
    @Before(value = "myPointcut()")
    public void myafterRet(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method= signature.getMethod();
        MyExitLog annotation=method.getAnnotation(MyExitLog.class);
        String operation=null;
        if (annotation!=null){
            operation=annotation.value();
        }
        // 获取用户Ip
//        String ip= IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());
        //获取当前操作的用户,从上下文中获取
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String email= (String) session.getAttribute("email");
        User user=userService.findUserByEmail(email);
        String username=user.getUsername();
        Integer userid=user.getId();

        //获取参数
        Object[] args = joinPoint.getArgs();
        Object toJSON = JSON.toJSON(args);
        //当前时间
        Date date = new Date();

        Log log = new Log();
        log.setUserid(userid).setCreateday(date).setEvent(operation).setCreatedate(date).setUsername(username);
        iLogManager.insertLog(log);



        System.out.println("当前用户:"+username+"时间:"+date+"--操作:"+operation+"--参数:"+toJSON);

        //


    }
}