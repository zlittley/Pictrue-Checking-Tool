package pers.joker.pct.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * 百度文字识别
 * 用来获取Access Token
 */
public class BaiduOCR {
    //官网获取的API Key
    private static final String apiKey = "YEVmDfACyq5u7h3Bkv5YFhHU";
    //官网获取的Secret Key
    private static final String secretKey = "B2xfP3jNapMKTYbBgbpnEP1CR5AVIqAK";
    //获取token字符串的链接
    private static final String authorHost = "https://aip.baidubce.com/oauth/2.0/token";

    public static String getAuthor(){
        return getAuthor(apiKey, secretKey);
    }

    public static String getAuthor(String ak, String sk){
        //获取token字段的请求地址 + （固定字段） + （官网获取的API Key） + （官网获取的Secret Key）
        String getAccessTokenUrl = authorHost + "?grant_type=client_credentials" + "&client_id=" + ak + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("POST");//百度推荐使用POST请求
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
//            System.err.println("result:" + result);
            JSONObject jsonObject = JSONObject.parseObject(result.toString());
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.println("获取token失败！");
            e.printStackTrace(System.err);
        }

        return null;
    }

    public static String getApiKey(){
        return apiKey;
    }

    public static String getSecretKey(){
        return secretKey;
    }
}
