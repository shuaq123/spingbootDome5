package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * employee
 * @author 
 */
@Data
@TableName(value = "employee")
public class Employee implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer employeeId;
    @NotNull(message = "员工姓名不能为空")
    private String employeeName;
    @NotNull(message = "手机号不能为空")
    private String employeePhone;
    private Integer state;
    private String password;
    private String avatar;
    /**
     * 0默认没有
     */
    private Integer rolePermission;
    private LocalDateTime creationTime;
    private static final long serialVersionUID = 1L;
}