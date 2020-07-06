package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author jamesBond
 * @createTime 2020/7/4/004 16:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
@TableName(value = "bolg_and_type")
public class BlogAndType {
    private Integer typeid;
    private Integer blogid;
}
