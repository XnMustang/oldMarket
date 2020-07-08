package com.wlrss.oldmarket.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author jamesBond
 * @createTime 2020/7/8/008 10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class GoodsList {
     private String goodsimg;
     private String goodsname;
     private String described;
     @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
     private String dateup;

}
