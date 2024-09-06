package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo4.pojo.po.BgDishes;
import com.example.demo4.pojo.po.BgExerciseStore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BgExerciseStoreDao extends BaseMapper<BgExerciseStore> {
    List<BgExerciseStore> ExerciseStoreList();
}