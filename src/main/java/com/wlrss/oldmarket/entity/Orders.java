package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)

@TableName(value = "orders")
public class Orders {

    @TableId(type = IdType.AUTO)
    private int id;
    private int userid;
    private long orderno; //编号
    private String accept;  //收件人
    private String address;
    private double money; //总金额
    private String status;  //状态码
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDown;      //下单时间

}
