package com.example.demo4.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.pojo.jo.FormList;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgFromInfo;
import com.example.demo4.pojo.po.BgUserImpotRecord;
import com.example.demo4.pojo.po.Employee;
import com.example.demo4.servers.BgFromInfoServers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/from")
public class BgFormManagement {


    private static final Logger logger = LogManager.getLogger(BgFormManagement.class);

    @Autowired
    private BgFromInfoServers bgFromInfoServers;

    @PostMapping("/add")
    public void add(@RequestBody Map<String,Object> jsonMap, HttpServletRequest request) {
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(jsonMap));
        var formAttribute = itemJSONObj.get("formAttribute");
        var bgFromInfo = new BgFromInfo();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(formAttribute);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(jsonString);
        bgFromInfo.setFormName(json.getString("formsName"));
        bgFromInfo.setRemark(json.getString("formsTextarea"));
        bgFromInfo.setCreationTime(LocalDateTime.now());
        var admin =(Employee) request.getAttribute("admin");
        bgFromInfo.setCreateId(admin.getEmployeeId());
        bgFromInfo.setState(1);
        bgFromInfo.setFormInfo(itemJSONObj);
        bgFromInfoServers.save(bgFromInfo);
    }
    @GetMapping("/get")
    public IPage<FormList> get(Search s){
        if (Objects.isNull(s.getJust())){
            System.out.println("没有穿");
            s.setCollection(1);
        }
        logger.info("This is an info log");
        logger.error("This is an error log");
        return bgFromInfoServers.getList(s);
    }
    
}
