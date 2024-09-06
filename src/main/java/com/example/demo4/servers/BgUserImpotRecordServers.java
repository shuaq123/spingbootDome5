package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgUserImpotRecordDao;
import com.example.demo4.pojo.jo.EmployeeInfo;
import com.example.demo4.pojo.po.BgUserImpotRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BgUserImpotRecordServers extends ServiceImpl<BgUserImpotRecordDao, BgUserImpotRecord> {

    @Autowired
    private BgUserImpotRecordDao bgUserImpotRecordDao;
    public IPage<BgUserImpotRecord> getUserRecord(){
        IPage<BgUserImpotRecord> topage = new Page<>(1,10);
        var importRecordPage = bgUserImpotRecordDao.getUserImportRecordPage(topage);
        return importRecordPage;
    }
}
