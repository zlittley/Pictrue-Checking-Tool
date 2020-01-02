package pers.joker.pct.utils;

import java.io.File;

/**
 * 文件操作工具类
 */
public class FileUtil {

    public static File getFile(String path){
        return new File(path);
    }

    public static String getBase64ImageString(String path){
        File image = getFile(path);
        String imageString = ImageBase64Encoder.encodeToBase64(image);
        //替换某些字符
        imageString = imageString.replaceAll("\r\n", "");
        imageString = imageString.replaceAll("\\+", "%2B");
        return imageString;
    }

    public static boolean isImage(String path){
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            return file.getName().endsWith(".png") || file.getName().endsWith(".jpg") || file.getName().endsWith(".jpeg") || file.getName().endsWith(".bmp");
        }else {
            return false;
        }
    }
}
