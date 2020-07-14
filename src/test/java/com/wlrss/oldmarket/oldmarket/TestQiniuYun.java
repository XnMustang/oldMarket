package com.wlrss.oldmarket.oldmarket;

import com.wlrss.oldmarket.utils.QiniuUpload;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 测试七牛云图片上传
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestQiniuYun {

    @Test
    public void test01(){
        QiniuUpload qiniuUpload = new QiniuUpload();
        /**
         * FilePath: 文件本地的绝对路径
         * FileName: 文件上传到七牛云的名称
         * 建议两个名称一致，这里可以通过控制台网址直接访问
         */
        String uploadPic = qiniuUpload.UploadPic("C:\\Users\\Acer\\Desktop\\材料.png", "材料.png");
        System.out.println(uploadPic);

    }

}
