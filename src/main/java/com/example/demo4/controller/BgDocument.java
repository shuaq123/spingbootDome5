package com.example.demo4.controller;


import com.example.demo4.config.JsonUtils;
import com.example.demo4.config.MD5Util;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

@RestController
@RequestMapping("/getdocument")
public class BgDocument {

    private Integer xin = 10;

    @GetMapping("/get")

    public void get() throws IOException {
        System.out.println(123);
    }

}
