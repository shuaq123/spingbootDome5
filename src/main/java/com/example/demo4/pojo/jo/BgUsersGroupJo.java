package com.example.demo4.pojo.jo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BgUsersGroupJo {
    private Integer groupId;
    private String groupName;

    /**
     * 分组层级
     */
    private Integer groupLeve;
    /**
     * 父级id
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 创建时间
     */
    private LocalDateTime creationTime;
    private Long creation;
}
