package com.example.demo4.pojo.jo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserImport implements Serializable {
    @ExcelProperty("用户名")
    private String userName;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("是否开启实名")
    private String isAuthentication;
    @ExcelProperty("性别")
    private String sex;
    @ExcelProperty("身份证")
    private String card;
    @ExcelProperty("失败原因")
    private String cause;
}
