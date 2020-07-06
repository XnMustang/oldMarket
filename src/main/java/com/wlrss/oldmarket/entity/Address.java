package com.wlrss.oldmarket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
public class Address {
    @TableId(type = IdType.AUTO)
    private Integer acceptid;
    private Integer userid;
    private String accept;
    private String address;
    private Integer isdefault;
    private String acceptphone;
}
