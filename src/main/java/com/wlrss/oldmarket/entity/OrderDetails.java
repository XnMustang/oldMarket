package com.wlrss.oldmarket.entity;

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
@TableName(value = "order_details")
@Accessors(chain = true)
public class OrderDetails {
    @TableId
    private  Integer id;
    private Integer nums;
    private  Integer ordersid;
    private   Integer goodsid;
}
