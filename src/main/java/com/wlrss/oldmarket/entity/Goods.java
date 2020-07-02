package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品实体类
 *
 * @author jamesBond
 * @createTime 2020/7/1/001 14:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@Validated
public class Goods {
    private Integer id;
    @NotEmpty(message = "商品名不能为空")
    private String goodsname;
    @Range(min = 0, message = "价格不能小于0")
    @NotEmpty(message = "价格不能为空")
    private BigDecimal price;
    @NotEmpty(message = "商品描述不能为空")
    private String described;
    private String goodsimg;
    private Integer userid;
    private Date date;
    @NotEmpty(message = "留言不能为空")
    private String sellmassage;
    private Integer status;
}
