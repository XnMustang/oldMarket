package com.wlrss.oldmarket.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class MyNewGoodsVo {

    private Integer goodsid;
    private String goodsName;
    private BigDecimal price;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateUp;

}
