package com.wlrss.oldmarket.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证邮箱格式
 */
public class EmailCheck {

    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public  static  boolean emailFormat(String email){
        boolean tag = true;
        final  String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher matcher = pattern.matcher(email);
        if (!matcher.find()){
            tag = false;
        }
        return  tag;
    }
}
