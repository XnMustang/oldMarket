package com.wlrss.oldmarket.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 接收留言信息
 */

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor

@Accessors(chain = true)
public class AcceptMsgInfoVo {

    private Integer acceptPerson;
    private String username;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date msgReplyTime;
    private String msgReply;
    private Integer sendPerson;

}
