package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NonNull;

/**
 * bg_users_group
 * @author 
 */
@Data
@TableName(value = "bg_users_group")
public class BgUsersGroup implements Serializable {
    /**
     * 分组id
     */
    @TableId(type = IdType.AUTO)
    private Integer groupId;

    /**
     * 分组名称
     */
    @NonNull
    private String groupName;

    /**
     * 分组层级
     */
    @NonNull
    private Integer groupLeve;
    /**
     * 父级id
     */
    @NonNull
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime creationTime;

    private static final long serialVersionUID = 1L;

    public BgUsersGroup() {
        // 无参构造函数
    }
}