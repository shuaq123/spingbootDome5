package com.example.demo4.pojo.jo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FormList {

    /***
     * 表单名称
     */
    private String formName;
    /***
     * 表单名称
     */
    private String stateName;

    /***
     * 今日采集
     */
    private Integer collectionToday;
    /***
     * 采集总数
     */
    private Integer totalCollection;
    /***
     * 引用位置数量
     */
    private Integer numberOitations;
    /***
     * 创建人
     */
    private String adminName;
    /***
     * 最近采集时间
     */
    private LocalDateTime collectionTime;
    /***
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    /***
     * 备注
     */
    private String remark;
}
