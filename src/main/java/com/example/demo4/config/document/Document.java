package com.example.demo4.config.document;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class Document {
    /***
     * 导出表 给特定字段指定下拉数据测试
     * @param classPathResource
     * @param response
     */
    public void putStream(ClassPathResource classPathResource, HttpServletResponse response){
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(classPathResource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取"男女"字段所在的单元格
        Sheet sheet = workbook.getSheetAt(0);
        var row = sheet.getRow(1);// 假设该字段在第二行
        String[] options = {"男", "女"};
        DataValidationHelper validationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint validationConstraint = validationHelper.createExplicitListConstraint(options);
        CellRangeAddressList addressList = new CellRangeAddressList(2, 1000, 4, 4);
        DataValidation validation = validationHelper.createValidation(validationConstraint, addressList);
        sheet.addValidationData(validation);

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("添加客户模版表-导出-修改.xlsx");
            try {
                var outputStream1 = response.getOutputStream();
                workbook.write(outputStream1);
                workbook.close();
                outputStream.close();
                outputStream1.flush();
                outputStream1.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
