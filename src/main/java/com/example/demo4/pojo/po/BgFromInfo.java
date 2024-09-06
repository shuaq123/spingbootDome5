package com.example.demo4.pojo.po;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 * bg_from_info
 * @author 
 */
@Data
@TableName(value = "bg_from_info")
public class BgFromInfo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer formId;

    private String formName;

    /**
     * 1.采集中 2停止采集
     */
    private Integer state;

    /**
     * 创建人
     */
    private Integer createId;

    private LocalDateTime creationTime;
    /**
     * 备注
     */
    private String remark;

    /**
     * 表单字段信息
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private JSONObject formInfo;

    private static final long serialVersionUID = 1L;
}