package com.example.demo4.pojo.jo;

import lombok.Data;

import java.io.Serializable;

@Data
public class menuInfo implements Serializable {
    /***
     * 菜单名称
     */
    private String menuName;
    /***
     * 价钱
     */
    private Integer menuPrice;
    /***
     * 备注
     */
    private String remark;
    /***
     * 图片集合
     */
    private String menuImgs;
}
