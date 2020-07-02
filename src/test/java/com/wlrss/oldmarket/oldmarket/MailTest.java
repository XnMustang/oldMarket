package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.entity.User;
import com.wlrss.oldmarket.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendMail(){
       /* User user =new User();
        user.setEmail("1617171230@qq.com");
        mailService.sendSimpleMail("1205212340@qq.com",
                "主题 ： 欢迎注册OldMarket",
                "此邮件为OldMarket用户注册激活邮件！请点击下面链接完成激活操作！此邮件30分钟有效! " +
                        "'http://localhost:8080/activation?email=" +user.getEmail());

        */
    }
}
