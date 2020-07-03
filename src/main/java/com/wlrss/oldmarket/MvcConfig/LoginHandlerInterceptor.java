package com.wlrss.oldmarket.MvcConfig;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *登录处理拦截器
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        Object loginInfo = request.getSession().getAttribute("email");
        if (loginInfo==null){
            //没有登录
            request.setAttribute("msg","请先登录");
            request.getRequestDispatcher("/login-register.html").forward(request,response);
            return false;
        }else {
         return true;
        }
    }

}
