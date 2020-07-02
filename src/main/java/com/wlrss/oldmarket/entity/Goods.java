package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)

@TableName(value = "goods")
public class Goods {

    private Integer id;
    private String goodsName;
    private BigDecimal price;
    private String described;
    private String goodsImg;
    private Integer userId;
    private Date date;
    private String sellMassage;
    private Integer status;


}
