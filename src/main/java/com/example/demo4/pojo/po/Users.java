package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * users
 * @author 
 */
@Data
@TableName(value = "users")
public class Users implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    @TableField(value = "phone_number")
    private String phoneNumber;
    private LocalDateTime lastTionTime;
    @TableField(value = "creation_time")
    private LocalDateTime creationTime;
    private static final long serialVersionUID = 1L;
}