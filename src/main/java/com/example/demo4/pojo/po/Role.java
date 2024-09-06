package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.Data;



/**
 * role
 * @author 
 */
@Data
@TableName(value = "role")
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer roleId;
//    @NonNull
    private String roleName;
    private String roleDescription;
//    @NonNull
    private Integer administrativeAuthority;
//    菜单id
    @TableField(exist = false) // 标注该属性为非数据库字段
    private String ids;
    @JsonIgnore
    @TableField(exist = false) // 标注该属性为非数据库字段
    private String jsonData;
    @TableField(exist = false) // 标注该属性为非数据库字段
    private Integer number;
    @TableField(exist = false) // 标注该属性为非数据库字段
    private List list;
    private static final long serialVersionUID = 1L;
}