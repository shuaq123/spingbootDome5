package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.pojo.po.BgPlatformMenuInfoRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BgPlatformMenuInfoRoleDao extends BaseMapper<BgPlatformMenuInfoRole> {
    int batchInsert(List<BgPlatformMenuInfoRole> list);
}