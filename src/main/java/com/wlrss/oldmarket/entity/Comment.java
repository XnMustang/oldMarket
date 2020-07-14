package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class Comment {
    private Integer id;
    private String content;
    private Integer selluserid;
    private Integer buyuserid;
    private Integer goodsid;
}
