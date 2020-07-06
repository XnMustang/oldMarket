package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author jamesBond
 * @createTime 2020/7/4/004 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "bolg_type")
public class BlogType {
    private Integer id;
    private String type;
}
