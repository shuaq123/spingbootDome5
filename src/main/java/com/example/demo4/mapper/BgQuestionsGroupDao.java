package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.pojo.po.BgQuestionsGroup;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BgQuestionsGroupDao extends BaseMapper<BgQuestionsGroup> {


    BgQuestionsGroup selectByPrimaryKey(Long groupId);

}