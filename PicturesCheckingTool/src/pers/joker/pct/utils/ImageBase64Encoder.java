package pers.joker.pct.utils;

import java.io.*;
import java.util.Base64;
import java.util.Base64.Encoder;

public class ImageBase64Encoder {

    /**
     * 将传入的图片文件进行base64编码处理
     * （百度文字识别要求图片为base64编码格式）
     * @param image 要编码的的图片文件
     * @return Base64编码过的字节数组字符串
     */
    public static String encodeToBase64(File image){
        //读取图片文件
        byte[] bytes = null;
        try {
            InputStream in = new FileInputStream(image);
            bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对读取到的图片数据进行base64编码
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }
}
