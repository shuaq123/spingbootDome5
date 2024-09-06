package com.example.demo4.pojo.jo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserInfo implements Serializable {

    private Integer userId;
    @ExcelProperty(value = "序号", index = 0)
    private Integer index;
    @ExcelProperty(value = "用户名", index = 1)
    private String userName;
    private String nickName;
    @ExcelProperty(value = "手机号", index = 2)
    private String phone;
    @ExcelProperty("密码")
    private String password;
    private Integer groupId;
    private String groupName;
    private Integer groupLeve;
    private Integer parentId;
    @ExcelProperty("是否开启实名")
    private String authentication;
    private Integer isAuthentication;
    private Integer employeeId;
    private String employeeName;
    @ExcelProperty("性别")
    private String sex2;
    private Integer sex;
    @ExcelProperty("身份证")
    private String card;
    private String remarks;
    /***
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
