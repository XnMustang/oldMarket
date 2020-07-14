package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Random;

/**
 * 爬虫对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Accessors(chain = true)

@TableName(value = "goods")
public class Content {

    private Integer goodsid;
    private String goodsname;
    private double price;
    private String described;
    private String goodsimg;
    private Integer userid;
    private Date dateUp;
    private String sellmassage;
    private Integer status;
    private Integer nums;

}
