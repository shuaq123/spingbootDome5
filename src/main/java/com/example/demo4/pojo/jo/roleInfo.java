package com.example.demo4.pojo.jo;

import com.example.demo4.pojo.po.BgPlatformMenuInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class roleInfo implements Serializable {

    private Integer roleId;
    //    @NonNull
    private String roleName;
    private String roleDescription;
    //    @NonNull
    private Integer administrativeAuthority;
    private Long cunt;
    private List<BgPlatformMenuInfo> list;
    private List<Long> ids;
}
