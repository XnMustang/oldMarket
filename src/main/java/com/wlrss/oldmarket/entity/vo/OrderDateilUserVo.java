package com.wlrss.oldmarket.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品参数  --  用户参数
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)    //支持链式编程

public class OrderDateilUserVo {
    //user表
    @TableField(value = "id")
    private Integer id;
    private String username;
    private String email;
    private String phone;

    //goods表
    private Integer goodsid;
    private String goodsname;
    private BigDecimal price;
    private String described;
    private Date dateUp;
    private String sellmassage;


}
