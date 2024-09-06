package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.pojo.jo.BgUsersGroupJo;
import com.example.demo4.pojo.po.BgUsersGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BgUsersGroupDao extends BaseMapper<BgUsersGroup> {
    List<BgUsersGroupJo> select();
}