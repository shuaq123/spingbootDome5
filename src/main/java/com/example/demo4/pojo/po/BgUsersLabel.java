package com.example.demo4.pojo.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg_users_label
 * @author 
 */
@Data
@TableName(value = "bg_users_label")
public class BgUsersLabel implements Serializable {
    /**
     * 学员标签id
     */
    @TableId(type = IdType.AUTO)
    private Integer labelId;

    /**
     * 标签名称
     */
    private String labelName;

    private static final long serialVersionUID = 1L;
}