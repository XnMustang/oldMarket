package com.wlrss.oldmarket.log;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Accessors(chain = true)
/**
 * 保存日志信息的类
 */
public class Log {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid; // 操作用户
    private String username;
    private String event; // 操作
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date createday;

}
