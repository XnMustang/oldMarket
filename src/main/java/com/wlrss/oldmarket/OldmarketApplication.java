package com.wlrss.oldmarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wlrss.oldmarket.mapper")

public class OldmarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(OldmarketApplication.class, args);
    }

}
