package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)

@TableName(value = "orders")
public class Orders {

    @TableId(type = IdType.AUTO)
    private int id;
    private User userId;
    private String orderNo;
    private String accept;  //收件人
    private String address;
    private Integer money;
    private String status;
    private Date date;      //结算时间

}
