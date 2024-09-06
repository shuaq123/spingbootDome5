package com.example.demo4.pojo.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Bg_users_impot_lose
 * @author 
 */
@Data
@TableName(value = "Bg_users_impot_lose")
public class BgUsersImpotLose implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer loseId;

    private String userName;

    private String password;

    private String phone;

    private String sex;

    private String cdre;

    private String cause;

    private String isAuthentication;

    private Long presentId;

    private static final long serialVersionUID = 1L;
}