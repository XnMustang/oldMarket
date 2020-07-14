package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.entity.Content;
import com.wlrss.oldmarket.mapper.ParseAddGoodsMapper;
import com.wlrss.oldmarket.utils.ParseUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParseAddGoods {

    @Autowired
    ParseAddGoodsMapper mapper;

    @Test
    public void test01() throws IOException {
        ParseUtils parseUtils = new ParseUtils();
        ArrayList<Content> contents = parseUtils.parseJD("photoshop");
        for (Content content : contents) {
            mapper.insert(content);
        }
    }

    //输出
    public static void main(String[] args) throws IOException {
        ParseUtils parseUtils = new ParseUtils();
        ArrayList<Content> contents = parseUtils.parseJD("python");
        for (Content content : contents) {
            System.out.println(content);
        }
    }

}
