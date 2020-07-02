package com.wlrss.oldmarket.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密
 */
public class MD5Util {
    public  static String EncoderByMd5(String password){
        MessageDigest md5 = null;

        try {
            //创建MD5核心对象
            md5 = MessageDigest.getInstance("MD5");
            //将字符码改为UTF-8
            md5.update(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte b[] = md5.digest();
        int i;
        StringBuffer buffer = new StringBuffer();
        for (int offset = 0 ; offset < b.length; offset++){
            //每次循环时候从digest数组中随机取出2个不同的 字符
            i = b[offset];
            if (i < 0 ){
                i += 256 ;
            }
            if (i < 16 ){
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(i));
        }
        return  buffer.toString();
    }

    /**
     * 加密规则
     */
    public static String finishMD5(String password){
        String MD5PWD = EncoderByMd5(
                EncoderByMd5(
                        EncoderByMd5(
                                EncoderByMd5(
                                        EncoderByMd5(
                                                EncoderByMd5(password))))));
        return  MD5PWD;
    }
}
