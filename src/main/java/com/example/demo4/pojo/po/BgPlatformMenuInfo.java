package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg_platform_menu_info
 * @author 
 */
@Data
@TableName(value = "bg_platform_menu_info")
public class BgPlatformMenuInfo implements Serializable {
    /**
     * 菜单的唯一标识
     */

    private Long id;

    /**
     * 上级菜单的编号
     */
    private String parentCode;

    /**
     * 菜单的层次序号
     */
    private Byte menuLayer;

    /**
     * 菜单的唯一编号
     */
    private String menuCode;

    /**
     * 菜单的显示文本
     */
    private String menuText;

    /**
     * 菜单的显示图标
     */
    private String menuIcon;

    /**
     * 同级菜单的排序
     */
    private Byte menuSort;

    /**
     * 是否有查看功能，0为否，1为是
     */
    private Byte menuShow;

    /**
     * 菜单的访问地址
     */
    private String visitUrl;

    /**
     * 菜单的路由路径
     */
    private String routePath;

    /**
     * 菜单的路由名称
     */
    private String routeName;

    /**
     * 添加人员的标识
     */
    private Long addId;

    /**
     * 添加人员的姓名
     */
    private String addName;

    /**
     * 添加人员的手机
     */
    private String addMobile;

    /**
     * 添加设备的IP
     */
    private String addIp;

    /**
     * 添加的日期时间
     */
    private Date addTime;

    private static final long serialVersionUID = 1L;

    public BgPlatformMenuInfo() {
        // 生成11位随机数
        UUID uuid = UUID.randomUUID();
        long ids = Math.abs(uuid.getMostSignificantBits()) % 100000000000L + 100000000000L;
        this.id  = ids;
    }
}