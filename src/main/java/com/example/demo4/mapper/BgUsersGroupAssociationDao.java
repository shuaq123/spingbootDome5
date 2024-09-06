package com.example.demo4.mapper;

import com.example.demo4.pojo.po.BgUsersGroupAssociation;

public interface BgUsersGroupAssociationDao {

    int deleteByPrimaryKey(Integer id);

    int insert(BgUsersGroupAssociation record);

    int insertSelective(BgUsersGroupAssociation record);

    BgUsersGroupAssociation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BgUsersGroupAssociation record);

    int updateByPrimaryKey(BgUsersGroupAssociation record);

    interface BgUsersDao {

    }
}