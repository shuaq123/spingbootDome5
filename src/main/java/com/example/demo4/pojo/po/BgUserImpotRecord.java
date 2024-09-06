package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * bg_user_impot_record
 * @author 
 */
@Data
@TableName(value = "bg_user_impot_record")
public class BgUserImpotRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer recordId;

    private Integer succeedNum;

    private Integer loseNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime impotTime;

    private Long presentId;

    private Integer tole;

    private static final long serialVersionUID = 1L;
}