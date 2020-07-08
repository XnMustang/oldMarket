package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.utils.QiniuUpload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试七牛云图片上传
 */
@SpringBootTest
public class TestQiniuYun {

    @Test
    public void test01(){
        QiniuUpload qiniuUpload = new QiniuUpload();
        String uploadPic = QiniuUpload.UploadPic("C:\\Users\\Acer\\Desktop\\七牛云图片测试\\下载.jpg", "布加迪1");
        System.out.println(uploadPic);

    }

}
