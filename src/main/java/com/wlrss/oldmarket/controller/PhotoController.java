package com.wlrss.oldmarket.controller;

import com.wlrss.oldmarket.utils.QiniuUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class PhotoController {

    @RequestMapping("/QiniuUpToken")
    @ResponseBody
    public String qiniuUpToken(String FilePath,String FileName){

        String allPath = FilePath+"/"+FileName;
        System.out.println("七牛云图片路径" + allPath);


        QiniuUpload qiniuUpload = new QiniuUpload();

        String uploadPic = qiniuUpload.UploadPic(FilePath, FileName);

        return uploadPic;
    }

}
