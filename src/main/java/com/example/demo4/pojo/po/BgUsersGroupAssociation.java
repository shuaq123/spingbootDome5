package com.example.demo4.pojo.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg_users_group_association
 * @author 
 */
@Data
@TableName(value = "bg_users_group_association")
public class BgUsersGroupAssociation implements Serializable {
    private Integer id;

    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 分组id
     */
    private Integer groupId;

    private static final long serialVersionUID = 1L;
}