package com.wlrss.oldmarket.log;

import com.alibaba.fastjson.JSON;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.service.ILogManager;
import com.wlrss.oldmarket.service.UserService;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
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
public class MyLogAdvice {
    @Autowired
    UserService userService;
    @Autowired
    ILogManager iLogManager;

    private Logger logger= Logger.getLogger(MyLogAdvice.class);

    @Pointcut("@annotation(com.wlrss.oldmarket.log.MyLog)")
    public void myPointcut(){

    }
    @AfterReturning(pointcut = "myPointcut()")
    public void myafterRet(JoinPoint joinPoint){
        MethodSignature signature=(MethodSignature)joinPoint.getSignature();
        Method method= signature.getMethod();
        MyLog annotation=method.getAnnotation(MyLog.class);
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
        if (email!=null){
            User user=userService.findUserByEmail(email);
            String username=user.getUsername();
            Integer userid=user.getId();

            //获取参数
            Object[] args = joinPoint.getArgs();
            Object toJSON = JSON.toJSON(args);
            //当前时间
            Date date = new Date();
            System.out.println("当前时间:"+date);
            Log log = new Log();
            log.setUserid(userid).setEvent(operation).setCreatedate(date).setUsername(username).setCreateday(date);
            iLogManager.insertLog(log);
            System.out.println("当前用户:"+username+"时间:"+date+"--操作:"+operation+"--参数:"+toJSON);
        }
        System.out.println("日志:登录错误!未找到 邮箱");






        //


    }
}
