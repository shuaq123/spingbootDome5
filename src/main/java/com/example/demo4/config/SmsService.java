package com.example.demo4.config;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class SmsService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Value("${sms.url}")
    private String url;
    @Value("${sms.account}")
    private String account;
    @Value("${sms.password}")
    private String password;
    @Value("${sms.sign}")
    private String sign;
    public void sendSms(String mobile) throws Exception {
        //短信下发
        Map map = new HashMap();
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        String randomNum = String.valueOf(num);
        redisTemplate.opsForValue().set(mobile,randomNum);
        redisTemplate.expire(mobile,300, TimeUnit.SECONDS);
        map.put("account",account);//API账号
        map.put("password",password);//API密码
        map.put("msg","【短信验证码】您的验证码是"+randomNum);//短信内容
        map.put("phone",mobile);//手机号
        JSONObject js = new JSONObject(map);
        System.out.println(sendSmsByPost(url,js.toString()));
        //查询余额
//        String balanceUrl = "https://smssh1.253.com/msg/balance/json";
//        Map map1 = new HashMap();
//        map1.put("account",account);
//        map1.put("password",password);
//        JSONObject js1 = new JSONObject(map1);
//        System.out.println(sendSmsByPost(balanceUrl,js1.toString()));
    }
    public static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.connect();
            OutputStream os=httpURLConnection.getOutputStream();
            os.write(postContent.getBytes("UTF-8"));
            os.flush();
            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}