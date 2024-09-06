package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo4.pojo.po.BgUserImpotRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BgUserImpotRecordDao extends BaseMapper<BgUserImpotRecord> {
    IPage<BgUserImpotRecord> getUserImportRecordPage(IPage<BgUserImpotRecord> page);
}