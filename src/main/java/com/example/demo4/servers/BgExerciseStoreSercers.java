package com.example.demo4.servers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgExerciseStoreDao;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgExerciseStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BgExerciseStoreSercers extends ServiceImpl<BgExerciseStoreDao, BgExerciseStore> {

    @Autowired
    private BgExerciseStoreDao BgExerciseStoreDao;
    public List<BgExerciseStore> ExerciseStoreList(){
        return BgExerciseStoreDao.ExerciseStoreList();
    }

}
