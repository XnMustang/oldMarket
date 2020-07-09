package com.wlrss.oldmarket.entity;

/**
 * 使用枚举类定义操作的类型：EventType。如登陆、添加、删除、更新、删除等
 */
public enum EventType {
    DEFAULT("1", "default"), ADD("2", "add"), UPDATE("3", "update"), DELETE_SINGLE("4", "delete-single"),
    LOGIN("10","login"),LOGIN_OUT("11","login_out");

    private String event;
    private String name;

    private EventType(String index, String name){
        this.name = name;
        this.event = index;
    }
    public String getEvent(){
        return this.event;
    }

    public String getName() {
        return name;
    }
}
