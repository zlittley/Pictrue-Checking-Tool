package pers.joker.pct.checker;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import pers.joker.pct.utils.BaiduOCR;
import pers.joker.pct.utils.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 图片文字识别类
 */
public class ImageTextRecognizer {
    //通用文字识别 - 50000次/天
    private static final String URL = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=";
    //通用文字识别（含位置信息版） - 500次/天
    private static final String URL_POS = "https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token=";
    //通用文字识别（高精度版） - 500次/天
    private static final String URL_ACC = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token=";
    //通用文字识别（高精度含位置版） - 50次/天
    private static final String URL_ACC_POS = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate?access_token=";

    private static final String ARGS = "detect_direction=true&id_card_side=front&image=";

    public static String extract(String path){
        String imageString = FileUtil.getBase64ImageString(path);   //base64编码的文件字符串
        //请求的url和args属性
        String requestUrl = URL_POS + BaiduOCR.getAuthor();
        String requestArgs = ARGS + imageString;

        String result = request(requestUrl, requestArgs);

        return convertJsonToString(result);
    }

    public static String extract(File file){
        return extract(file.getPath());
    }

    //向百度发送请求并得到识别结果
    private static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            // 用java JDK自带的URL去请求
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 设置该请求的消息头
            // 设置HTTP方法：POST
            connection.setRequestMethod("POST");
            // 设置其Header的Content-Type参数为application/x-www-form-urlencoded
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 填入API Key到HTTP header
            connection.setRequestProperty("apikey", "uml8HFzu2hFd8iEG2LkQGMxm");
            // 将第二步获取到的token填入到HTTP header
            connection.setRequestProperty("access_token", BaiduOCR.getAuthor());
            connection.setDoOutput(true);
            connection.getOutputStream().write(httpArg.getBytes("UTF-8"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //将json数据转化成arrayList
    private static ArrayList<String> convertJsonToArrayList(String json){
        ArrayList<String> list = new ArrayList<>();
        JSONObject jsonObject = JSONObject.parseObject(json.toString());
        //根据返回json数据的结构，其中的words_result字段为一个json数组，将其提取出来
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        //遍历words_result，并将words字段对应的值添加到arrayList中
        for(int i = 0; i < words_result.size(); i++){
            JSONObject result = words_result.getJSONObject(i);
            String value = result.getString("words");
            list.add(value);
        }
        return list;
    }

    //将json数据中有用的信息提取出来，整合成一段完整的文字
    private static String convertJsonToString(String json){
        StringBuffer buffer = new StringBuffer(); //使用StringBuffer以提高效率以及减少空间占用
        JSONObject jsonObject = JSONObject.parseObject(json.toString());
        JSONArray words_result = jsonObject.getJSONArray("words_result");
        for(int i = 0; i < words_result.size(); i++){
            JSONObject result = words_result.getJSONObject(i);
            String value = result.getString("words");
            buffer.append(value);
        }
        return buffer.toString();
    }

}
