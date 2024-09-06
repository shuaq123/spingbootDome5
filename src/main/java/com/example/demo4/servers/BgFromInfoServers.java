package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgFromInfoDao;
import com.example.demo4.pojo.jo.FormList;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgFromInfo;
import com.example.demo4.pojo.po.BgUserImpotRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BgFromInfoServers extends ServiceImpl<BgFromInfoDao, BgFromInfo> {
    @Autowired
    private BgFromInfoDao bgFromInfoDao;

    public IPage<FormList> getList(Search s){
        IPage<FormList> topage = new Page<>(s.getCurrentPage(),s.getPageSize());
        var bgUserImpotRecordIPage = bgFromInfoDao.selectVo(topage,s);
        bgUserImpotRecordIPage.getRecords().stream().map(form -> {
            form.setTotalCollection(0);
            return form;
        }).collect(Collectors.toList());
        return bgUserImpotRecordIPage;
    }
}
