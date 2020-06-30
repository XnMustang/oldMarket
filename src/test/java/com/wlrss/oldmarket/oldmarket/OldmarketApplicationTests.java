package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.mapper.UserMapper;
import lombok.ToString;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class OldmarketApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        System.out.println(dataSource.getClass());
    }

    @Autowired
    UserMapper userMapper;

    @Test
    public  void  findBoss(){
        //User boss = userMapper.selectById(1);
       // System.out.println(boss);
        List<User> users = userMapper.selectList(null);
        users.forEach(user -> System.out.println(user));
    }
}
