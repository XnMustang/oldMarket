package com.wlrss.oldmarket.entity;

/**
 * 使用枚举类定义功能模块类型ModuleType
 */
public enum  ModuleType {
    DEFAULT("1"),
    USER("2");


    private String module;

    public String getModule(){
        return this.module;
    }
    private ModuleType(String index) {
       this.module=index;
    }
}
