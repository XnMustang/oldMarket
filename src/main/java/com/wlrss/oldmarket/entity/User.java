package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "user")
public class User {

    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;
    private  String username;
    private  String password;
    private  String email;
    private  String phone;
    private  String gender;
    private  String qq;
    private  String google;
    private  String   intro;
    private  String  headimg;
    private  String status;
    private  String vip;
    private  Integer goodscount;
}
