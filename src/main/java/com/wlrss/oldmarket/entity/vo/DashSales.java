package com.wlrss.oldmarket.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jamesBond
 * @createTime 2020/7/7/007 14:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class DashSales {
    private String goodsname;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date dateup;
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date datedown;
    private String email;
    private BigDecimal price;

}
