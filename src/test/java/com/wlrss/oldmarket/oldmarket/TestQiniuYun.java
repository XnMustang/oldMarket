package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.utils.QiniuUpload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试七牛云图片上传
 */
@SpringBootTest
public class TestQiniuYun {

    @Autowired
    QiniuUpload qiniuUpload;

    @Test
    public void test01(){
        /**
         * FilePath: 文件本地的绝对路径
         * FileName: 文件上传到七牛云的名称
         * 建议两个名称一致，这里可以通过控制台网址直接访问
         */
        String uploadPic = QiniuUpload.UploadPic("C:\\Users\\Acer\\Desktop\\world6.jpg", "world6.jpg");
        System.out.println(uploadPic);

    }

}
