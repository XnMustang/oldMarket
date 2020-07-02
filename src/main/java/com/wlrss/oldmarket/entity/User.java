package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

/**
 * @author jamesBond
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "user")
public class User {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "username")
    private String userName;
    @TableField(value = "password")
    private String passWord;
    private String email;
    private String phone;
    private String gender;
    private String qq;
    private String google;
    private String intro;
    @TableField(value = "headimg")
    private String headImg;
    private String status;
    private String vip;

    private Integer goodscount;
    private Integer goodscountyet;
}
