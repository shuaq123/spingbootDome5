package com.example.demo4.config;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo4.pojo.jo.UserImport;
import com.example.demo4.pojo.jo.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelReader extends AnalysisEventListener<UserImport> {
    private List<UserImport> dataList = new ArrayList<>();
    private boolean isHeadRow = false; // 表示当前行是否为表头行

    @Override
    public void invoke(UserImport user, AnalysisContext analysisContext) {
        // 将每一行数据存储到数据库中
        // ...
        dataList.add(user);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 数据导入完成后的操作，例如关闭数据库连接等
        // ...
    }

    public List<UserImport> getDataList() {
        return dataList;
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        // 如果当前行是表头行，则将isHeadRow标记为true
        if (context.getCurrentRowNum() == 1) {
            isHeadRow = true;
        }
    }

}