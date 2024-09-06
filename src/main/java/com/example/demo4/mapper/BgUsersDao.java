package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo4.pojo.jo.UserInfo;
import com.example.demo4.pojo.jo.searchName;
import com.example.demo4.pojo.po.BgUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface BgUsersDao extends BaseMapper<BgUsers> {

    IPage<UserInfo> selectUserList(IPage<UserInfo> page,
                                   @Param("searchname") searchName searchname,
                                   @Param("groupList") List<Integer> groupList);
}