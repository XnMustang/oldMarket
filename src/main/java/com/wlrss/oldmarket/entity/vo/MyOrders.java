package com.wlrss.oldmarket.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * vo返回值实现我的订单的功能
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Accessors(chain = true)

public class MyOrders {

    private String goodsName;
    private Date date;
    private String email;
    private String orderNo;
    private Integer money;
    private String status;
}
