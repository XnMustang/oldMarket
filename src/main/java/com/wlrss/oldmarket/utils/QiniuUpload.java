package com.wlrss.oldmarket.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 * 七牛云上传文件到服务器
 */
@Component
public class QiniuUpload {

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = VariableName.accessKey; //这两个登录七牛 账号里面可以找到
    private static String SECRET_KEY = VariableName.secretKey;

    //要上传的空间
    private static String bucketname = VariableName.bucket; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    private static Configuration cfg = new Configuration(Zone.huadong());
    //创建上传对象

    private static UploadManager uploadManager = new UploadManager(cfg);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public static String getUpToken(){
        return auth.uploadToken(bucketname);
    }


    /**
     * 这个方法是根据图片的绝对路径来保存的
     * @param FilePath      第一个参数就是图片的绝对路径
     * @param FileName      第二个参数就是保存在七牛云空间的图片名称
     * @return
     */
    public static String UploadPic(String FilePath,String FileName){
        Configuration cfg = new Configuration(Zone.huadong());
        UploadManager uploadManager = new UploadManager(cfg);
        String accessKey = VariableName.accessKey;      //AccessKey的值
        String secretKey = VariableName.secretKey;      //SecretKey的值
        String bucket = VariableName.bucket;            //存储空间名
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(FilePath, FileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return VariableName.domain+"/"+FileName;

        }catch (QiniuException ex){
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return null;
    }

    /**
     * 这个方法就是根据Spring MVC式的MultipartFile类型的图片进行上传的
     * @param file
     * @param filename      第二个参数就是保存在七牛云空间的图片名称
     * @return
     * @throws Exception
     */
    public static String updateFile(MultipartFile file, String filename) throws Exception {
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            InputStream inputStream = file.getInputStream();
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[600]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }

            byte[] uploadBytes  = swapStream.toByteArray();
            try {
                Response response = uploadManager.put(uploadBytes,filename,getUpToken());
                //解析上传成功的结果
                DefaultPutRet putRet;
                putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                return VariableName.domain+putRet.key;

            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (UnsupportedEncodingException ex) {
        }
        return null;
    }

    /**
     * 完成以上步骤，就可以实现图片上传了，这两个方法的返回值图片的地址，可以直接复制到浏览器预览的。
     */

}
