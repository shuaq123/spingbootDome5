package com.example.demo4.pojo.jo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo4.enumeration.ResultCode;
import com.example.demo4.pojo.po.BgPlatformMenuInfoRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EmployeeInfo implements Serializable {

    private ResultCode resultCode;
    private Integer employeeId;
    private String employeeName;
    private String employeePhone;
    private String password;
    private Integer state;
    private String avatar;
    private Integer rolePermission;
    private List<BgPlatformMenuInfoRole> list;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creationTime;
    private Integer roleId;
    private String stateName;
    //    @NonNull
    private String roleName;
}
