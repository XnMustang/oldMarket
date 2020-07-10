package com.wlrss.oldmarket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 爬虫对象
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Content {

    private String title;
    private String img;
    private String price;
}
