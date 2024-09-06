package com.example.demo4.mapper;

import com.example.demo4.pojo.po.BgUsersLabel;

public interface BgUsersLabelDao {
    int deleteByPrimaryKey(Integer labelId);

    int insert(BgUsersLabel record);

    int insertSelective(BgUsersLabel record);

    BgUsersLabel selectByPrimaryKey(Integer labelId);

    int updateByPrimaryKeySelective(BgUsersLabel record);

    int updateByPrimaryKey(BgUsersLabel record);
}