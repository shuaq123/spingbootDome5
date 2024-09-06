package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * bg_users
 * @author 
 */
@Data
@Accessors(chain = true) //链式写法
@TableName(value = "bg_users")
public class BgUsers implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登陆密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 管理员id
     */
    private Integer employeeId;

    /**
     * 状态1.生效 2失效
     */
    private Integer stuats;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别:1男 2女
     */
    private Integer sex;
    /**
     * 所属分组
     */
    private Integer groupId;
    /**
     * 身份证
     */
    private String card;

    /**
     * 是否实名认证
     */
    private Integer isAuthentication;

    /**
     * 联系方式
     */
    private Integer contact;

    private LocalDateTime createTime;
    /**
     * 备注
     */
    private String remarks;

    private static final long serialVersionUID = 1L;

}