package com.wlrss.oldmarket.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 留言信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Accessors(chain = true)
public class MessageInfoVo {

    private Integer sendPerson;     //发送人id
    private String username;        //发送人姓名
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date msgContentTime;    //发送留言时间
    private String msgContent;      //发送内容
    private Integer acceptPerson;   //接收人id

}
