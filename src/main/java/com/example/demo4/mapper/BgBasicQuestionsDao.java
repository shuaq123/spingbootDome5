package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgBasicQuestions;
import com.example.demo4.pojo.po.BgDishes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BgBasicQuestionsDao extends BaseMapper<BgBasicQuestions> {
    IPage<BgBasicQuestions> findQuestion(IPage<BgBasicQuestions> page,@Param("info") Search info);
}