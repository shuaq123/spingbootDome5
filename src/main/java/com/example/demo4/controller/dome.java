package com.example.demo4.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo4.pojo.po.BgExerciseStore;
import com.example.demo4.servers.BgExerciseStoreSercers;
import com.example.demo4.servers.DomeSerces;
import com.example.demo4.servers.JIU.B2AgencyServer;
import lombok.extern.slf4j.Slf4j;
import com.example.demo4.config.OSSConfig;
import com.example.demo4.config.SmsService;
import com.example.demo4.config.phone.phone;
import com.example.demo4.servers.UserImp;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
@RequestMapping("/dome")
public class dome {

    @Autowired
    private BgExerciseStoreSercers bgExerciseStoreSercers;
    @Autowired
    private OSSConfig ossConfig;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserImp userImp;
    @Autowired
    private B2AgencyServer b2AgencyServer;
    @Autowired
    private SmsService smsService;
    @Autowired
    private DomeSerces domeSerces;
//    @Value("${application.version}")
//    private Integer name;

    /***
     * 测试类数据写入word文档
     * @return
     */
    @PostMapping("/dataInputWord")
    public void loginUser(MultipartFile file, HttpServletResponse response) throws IOException {
        var list = bgExerciseStoreSercers.ExerciseStoreList();
        domeSerces.addWord(list);
    }

    /***
     * 验证码发送 5分钟失效时间
     * @param p
     */
    @PostMapping("/sendVerification")
    public void sendVerification(String p) {
        var phone = new phone();
        var phoneValid = phone.isPhoneValid(p);
        if (phoneValid) {
            try {
                smsService.sendSms(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @PostMapping("/ceshi")
    public String file(MultipartFile file) {
        return "2312343234234234";
    }

    /***
     * 数据同步
     */
    @PostMapping("/dome")
    public void sendVerification() {
        System.out.println("dome");
        var list = userImp.list();
        System.out.println("1.0金必过");
        System.out.println(list);
    }

    /***
     * 数据同步
     */
    @Transactional("secondaryTransactionManager") // 指定使用 secondaryTransactionManager
    @PostMapping("/doome")
    public void dome() {
        System.out.println("dome");
        var list = b2AgencyServer.list();
        System.out.println("旧必过");
        System.out.println(list);
    }


}


