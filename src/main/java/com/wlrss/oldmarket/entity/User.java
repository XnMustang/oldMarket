package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String username;
    private String password;
    private String email;
    private String phone;
    private String gender;
    private String qq;
    private String google;
    private String intro;
    private String headimg;
    private String status;
    private String vip;
    private Integer goodscount;
    private Integer goodscountyet;
}
