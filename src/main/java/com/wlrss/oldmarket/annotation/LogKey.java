package com.wlrss.oldmarket.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 此注解如果注解在方法上，则整个方法的参数以json的格式保存到日志中。
 * 如果此注解同时注解在方法和类上，则方法上的注解会覆盖类上的值。
 */
public @interface LogKey {
    String keyName() default ""; // key的名称
    boolean isUserId() default false; // 此字段是否是本次操作的userId，这里略
    boolean isLog() default true; // 是否加入到日志中
}
