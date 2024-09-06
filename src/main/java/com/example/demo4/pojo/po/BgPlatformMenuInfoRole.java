package com.example.demo4.pojo.po;

import java.io.Serializable;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * bg_platform_menu_info_role
 * @author 
 */
@Data
@TableName(value = "bg_platform_menu_info_role")
public class BgPlatformMenuInfoRole implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer roleid;

    private Long bgPlatformMenuInfo;

    private static final long serialVersionUID = 1L;


}