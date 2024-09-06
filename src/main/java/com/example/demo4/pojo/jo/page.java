package com.example.demo4.pojo.jo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class page {

    private int pageNum;
    private int size;
    @ExcelProperty("性别")
    private String sex;
}
