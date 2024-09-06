package com.example.demo4.pojo.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg_users_label_association
 * @author 
 */
@Data
@TableName(value = "bg_users_label_association")
public class BgUsersLabelAssociation implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学生id
     */
    private Integer userId;

    /**
     * 标签id
     */
    private Integer labelId;

    private static final long serialVersionUID = 1L;
}