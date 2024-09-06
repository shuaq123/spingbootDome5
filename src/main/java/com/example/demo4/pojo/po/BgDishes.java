package com.example.demo4.pojo.po;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * bg_dishes
 * @author 
 */
@Data
@TableName(value = "bg_dishes")
public class BgDishes implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer menuId;
    /**
     * 菜品名称
     */
    private String menuName;

    /**
     * 价钱
     */
    private Integer menuPrice;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜品图片照集
     */
    private String menuImgs;
    private String menuStep;
    private Integer adminId;
    @TableField(exist = false)
    private String adminName;
    @TableLogic
    private Integer deletedFlag;
    private static final long serialVersionUID = 1L;
}