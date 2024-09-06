package com.example.demo4.servers;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgUsersGroupDao;
import com.example.demo4.mapper.BgUsersImpotLoseDao;
import com.example.demo4.pojo.jo.UserImport;
import com.example.demo4.pojo.jo.UserInfo;
import com.example.demo4.pojo.po.BgUsersGroup;
import com.example.demo4.pojo.po.BgUsersImpotLose;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class BgUsersImpotLoseServers extends ServiceImpl<BgUsersImpotLoseDao, BgUsersImpotLose> {
    public void inResponse(HttpServletResponse response, List<UserImport> exporList){
        ClassPathResource classPathResource = new ClassPathResource("template/用户导入失败记录表.xlsx");
        String fileName = "用户失败记录-导出";
        try {
            preExportExcel(response, fileName);
            EasyExcel.write(response.getOutputStream())
                    .withTemplate(classPathResource.getInputStream())
                    .sheet()
                    .doFill(() -> {
                        // 分页查询数据
                        return exporList;
                    });
        } catch (IOException e) {
            System.out.println(e);

        }
    }

    private void preExportExcel(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String filename = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        String disposition = "attachment;filename*=utf-8''" + filename + ".xlsx";
        response.setHeader("Content-Disposition", disposition);
    }
}
