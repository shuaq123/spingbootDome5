package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgUsersGroupDao;
import com.example.demo4.pojo.jo.BgUsersGroupJo;
import com.example.demo4.pojo.po.BgPlatformMenuInfo;
import com.example.demo4.pojo.po.BgPlatformMenuInfoRole;
import com.example.demo4.pojo.po.BgUsersGroup;
import com.example.demo4.pojo.po.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BgUsersGroupServers extends ServiceImpl<BgUsersGroupDao, BgUsersGroup> {
    @Autowired
    private BgUsersGroupDao usersGroupDao;

    public void addUserGroup(BgUsersGroup usersGroup){
        usersGroup.setCreationTime(LocalDateTime.now());
        usersGroup.setSort(1);
        var insert = usersGroupDao.insert(usersGroup);
    }
    public List<BgUsersGroupJo> getUserGroup(){
        var groups = usersGroupDao.select();;
        return groups;
    }
    public void getMapper(){
        LambdaQueryWrapper<BgUsersGroup> wrapper = new LambdaQueryWrapper<>();
        var list = this.list(wrapper);
        System.out.println(list);
    }



}
