package com.wlrss.oldmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author jamesBond
 * @createTime 2020/7/4/004 15:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Blog {
    private Integer id;
    private String title;
    private Integer userid;
    private String img;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String stasus;

}
