package com.example.demo4.servers;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgPlatformMenuInfoDao;
import com.example.demo4.pojo.po.BgPlatformMenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BgPlatformMenuInfoImp extends ServiceImpl<BgPlatformMenuInfoDao, BgPlatformMenuInfo> {
    @Autowired
    private BgPlatformMenuInfoDao bgPlatformMenuInfoDao;

    public List<Map<String,Object>> getBgPlatformMenuInfo(){
        var menuInfos = bgPlatformMenuInfoDao.selectList(new QueryWrapper<BgPlatformMenuInfo>().lambda());
        List<Map<String,Object>> objects = new ArrayList<>();
        var collect = menuInfos.stream().filter(c -> c.getMenuLayer() == 1).collect(Collectors.toList());
        collect.forEach(c -> {
            HashMap<String,Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("id",c.getId());
            objectObjectHashMap.put("label",c.getMenuText());
            List<Map<String,Object>> children = new ArrayList<>();
            LambdaQueryWrapper<BgPlatformMenuInfo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(BgPlatformMenuInfo::getParentCode,c.getMenuCode());
            var bgPlatformMenuInfo = bgPlatformMenuInfoDao.selectList(wrapper);
            bgPlatformMenuInfo.forEach(y -> {
                HashMap<String,Object> objectObjectHashMap2 = new HashMap<>();
                objectObjectHashMap2.put("id",y.getId());
                objectObjectHashMap2.put("label",y.getMenuText());
                children.add(objectObjectHashMap2);

            });
            objectObjectHashMap.put("children",children);
            objects.add(objectObjectHashMap);
        });
        System.out.println(objects);
        return objects;
    }

}
