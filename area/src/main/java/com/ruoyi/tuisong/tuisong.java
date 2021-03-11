package com.ruoyi.tuisong;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

/*
* 推送开门信息到LeanCloud
* */
public class tuisong {
    public void fasong(long cabinetId, long putinId,String key){
        String AppID = "EgF0MfVe2FXebcJerWLrDVtV-gzGzoHsz";
        String AppKey = "U6BBfO9ixwTXSBUFpue1udME";
        String MasterKey = "iu8Mv6rv1v0AJXEGarkAlzoN";
        AVOSCloud.initialize(AppID, AppKey, MasterKey);

        long time = new Date().getTime()/1000; //获取当前时间戳

        String cabinet = String.valueOf(cabinetId);
        String putin = String.valueOf(putinId);
        String createtime= String.valueOf(time);
        String zhiling = "f"+putin+cabinet+createtime+"f";
        byte[] b = encryption(zhiling,key);
       // System.out.println("1234解密后的值：" +new String(decipher(new String(b), key)));
        AVObject test = new AVObject("tuisong");
        test.put("id",new String(b));
        try {
            test.save();
        } catch (AVException e) {
            e.printStackTrace();
        }
    }
    public static byte[] encryption(String content,String key){
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();

        byte dkey = 0;
        for(byte b : keyBytes){
            dkey ^= b;
        }

        byte salt = 0;  //随机盐值
        byte[] result = new byte[contentBytes.length];
        for(int i = 0 ; i < contentBytes.length; i++){
            salt = (byte)(contentBytes[i] ^ dkey ^ salt);
            result[i] = salt;
        }
        return result;
    }






   /*解密
   public static byte[] decipher(String content,String key){
        byte[] contentBytes = content.getBytes();
        byte[] keyBytes = key.getBytes();

        byte dkey = 0;
        for(byte b : keyBytes){
            dkey ^= b;
        }

        byte salt = 0;  //随机盐值
        byte[] result = new byte[contentBytes.length];
        for(int i = contentBytes.length - 1 ; i >= 0 ; i--){
            if(i == 0){
                salt = 0;
            }else{
                salt = contentBytes[i - 1];
            }
            result[i] = (byte)(contentBytes[i] ^ dkey ^ salt);
        }
        return result;
    }*/
}
