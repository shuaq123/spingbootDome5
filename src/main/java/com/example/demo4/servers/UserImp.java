package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgUsersDao;
import com.example.demo4.mapper.UsersDao;
import com.example.demo4.pojo.jo.EmployeeInfo;
import com.example.demo4.pojo.jo.UserInfo;
import com.example.demo4.pojo.jo.searchName;
import com.example.demo4.pojo.po.BgUsers;
import com.example.demo4.pojo.po.BgUsersGroup;
import com.example.demo4.pojo.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserImp extends ServiceImpl<BgUsersDao, BgUsers> {

    @Autowired
    private BgUsersDao bgUsersDao;
    @Autowired
    private BgUsersGroupServers bgUsersGroupServers;

     public IPage<UserInfo> listUserInfo(searchName searchname){
         var groupId = searchname.getGroupId();
         LambdaQueryWrapper<BgUsersGroup> wrapper = new LambdaQueryWrapper<>();
         wrapper.eq(BgUsersGroup::getParentId,groupId);
         List<Integer> groupList = new ArrayList<>();
         var list = bgUsersGroupServers.list(wrapper);
         if (Objects.nonNull(list)){
             for (BgUsersGroup group:list){
                 groupList.add(group.getGroupId());
                 LambdaQueryWrapper<BgUsersGroup> wrappe1 = new LambdaQueryWrapper<>();
                 wrappe1.eq(BgUsersGroup::getParentId,group.getGroupId());
                 var list1 = bgUsersGroupServers.list(wrappe1);
                 if (Objects.nonNull(list1)){
                     list1.forEach(v -> {
                         groupList.add(v.getGroupId());
                     });
                 }
             }
         }
         groupList.add(searchname.getGroupId());
         IPage<UserInfo> page = new Page<>(searchname.getPageName(),searchname.getSize());
         return bgUsersDao.selectUserList(page,searchname,groupList);
     }

}
