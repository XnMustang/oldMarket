package com.wlrss.oldmarket.annotation;

import com.wlrss.oldmarket.entity.EventType;
import com.wlrss.oldmarket.entity.ModuleType;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD, ElementType.TYPE})
/**
 * 这里定义日志的详细内容。如果此注解注解在类上，则这个参数做为类全部方法的默认值。
 * 如果注解在方法上，则只对这个方法启作用
 */
public @interface LogEvent {
    ModuleType module() default ModuleType.DEFAULT; // 日志所属的模块
    EventType event() default EventType.DEFAULT; // 日志事件类型
    String desc() default  ""; // 描述信息
}