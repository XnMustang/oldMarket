package com.wlrss.oldmarket.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
//查用户表,地址表获取个人信息,用MyUser类接收
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
//username,email,`user`.phone,address,gender,qq,google,intro
public class MyUser {
    private Integer id;
    private Integer acceptid;
    private String username;
    private String email;
    private String phone;
    private String gender;
    private String qq;
    private String google;
    private String intro;
    private String headimg;
    //private String status;
    //private String vip;
    private String address;
//    @TableField(value = "'address'.id")
//    private Integer addressId;
    private String accept;
    private String acceptPhone;
    private Integer isdefault;

}
