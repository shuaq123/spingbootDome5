package com.example.demo4.mapper;

import com.example.demo4.pojo.po.BgUsersLabelAssociation;

public interface BgUsersLabelAssociationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BgUsersLabelAssociation record);

    int insertSelective(BgUsersLabelAssociation record);

    BgUsersLabelAssociation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BgUsersLabelAssociation record);

    int updateByPrimaryKey(BgUsersLabelAssociation record);
}