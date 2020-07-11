package com.wlrss.oldmarket.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
/**
 * 保存日志信息的类
 */

public class LogAdmModel {
    private Long id;
    private String userId; // 操作用户
    private String userName;
    private String admModel; // 模块
    private String admEvent; // 操作
    private Date createDate; // 操作内容
    private String admOptContent; // 操作内容
    private String desc; // 备注
}
