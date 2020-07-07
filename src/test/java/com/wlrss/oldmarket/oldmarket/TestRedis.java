package com.wlrss.oldmarket.oldmarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class TestRedis {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void  test2(){
        redisUtil.set("name","刘轶");
        System.out.println(redisUtil.get("name"));
    }

    @Test
    public  void  testRedis(){
        /**
         * 操作不同的数据类型  和指令是一样的
         * opsForValue 操作字符串  类似string
        *      opsForList
        *      opsForLSet
        *      opsForHash
        *      opsForGeo
         */

        //获取链接对象
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        //清除所有的ke
        //connection.flushAll();

        //操作字符串
        redisTemplate.opsForValue().set("myKey","刘轶");
        System.out.println(redisTemplate.opsForValue().get("myKey"));
    }


    /**
     * 关于对象的 传递
     * 企业中所有pojo基本都会序列化
     *
     * @throws JsonProcessingException
     */
    @Test
    public  void  Test() throws JsonProcessingException {
        //真实开发一般都是使用json传递对象
        User user = new User();
        user.setEmail("123@qq.com");
        user.setUsername("刘轶");
        //把对象转为json字符串
        //String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisUtil.set("user",user);
        System.out.println(redisUtil.get("user"));
    }
}
