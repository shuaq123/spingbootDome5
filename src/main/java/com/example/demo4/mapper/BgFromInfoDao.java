package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo4.pojo.jo.FormList;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgFromInfo;
import com.example.demo4.pojo.po.BgUserImpotRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BgFromInfoDao extends BaseMapper<BgFromInfo> {
    IPage<FormList> selectVo(IPage<FormList> page,
                             @Param("search") Search search);


}