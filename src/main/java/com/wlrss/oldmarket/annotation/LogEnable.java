package com.wlrss.oldmarket.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogEnable {
    /**
     * 定义日志的开关量，类上只有这个值为true，这个类中日志功能才开启
     * 如果为true，则类下面的LogEvent启作用，否则忽略
     * @return
     */
    boolean logEnable() default true;
}
