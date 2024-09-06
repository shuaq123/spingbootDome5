package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg_questions_group
 * @author 
 */
@Data
@TableName(value = "bg_questions_group")
public class BgQuestionsGroup implements Serializable {
    /**
     * 题组id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long groupId;

    /**
     * 题组名称
     */
    private String groupName;

    /**
     * 层级
     */
    private Integer leve;

    /**
     * 父级
     */
    private Long prentId;

    /**
     * 创建时间
     */
    private LocalDateTime creationTime;

    private static final long serialVersionUID = 1L;



}