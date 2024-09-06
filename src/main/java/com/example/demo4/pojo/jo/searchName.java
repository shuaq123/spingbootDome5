package com.example.demo4.pojo.jo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class searchName implements Serializable {

    /***
     * 是否开课
     */
    private Integer isOpen;
    /***
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /***
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    /***
     * 所属管理员
     */
    private Integer adminId;
    /***
     * 正式学员名称/昵称/手机号
     */
    private String condition;
    /***
     * 哪页
     */
    private Integer pageName;
    /***
     * 数量
     */
    private Integer size;
    /***
     * 分组id
     */
    private Integer groupId;

}
