package com.example.demo4.pojo.po;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.*;
import com.example.demo4.enumeration.questionType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * bg_basic_questions
 * @author 
 */
@Data
@TableName(value = "bg_basic_questions")
public class BgBasicQuestions implements Serializable {
    /**
     * 题目id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long questionsId;

    /**
     * 题干
     */
    private String questionsName;

    /**
     * 分类
     */
    private Long questionsClass;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 选项
     */
    private String options;

    /**
     * 答案
     */
    private String answer;

    /**
     * 解析
     */
    private String analysis;
    @TableLogic
    private Integer deletedFlag;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    @TableField(exist = false)
    private com.example.demo4.enumeration.questionType questiontype;
    @TableField(exist = false)
    private String id;
    @TableField(exist = false)
    private String groupName;
    @TableField(exist = false)
    private String questionsClassSting;
    /**
     * 类型名称
     */
    @TableField(exist = false)
    private String typeName;
    private static final long serialVersionUID = 1L;
}