package com.example.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo4.pojo.jo.EmployeeInfo;
import com.example.demo4.pojo.jo.page;
import com.example.demo4.pojo.po.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

@Mapper
public interface EmployeeDao extends BaseMapper<Employee>{
    IPage<EmployeeInfo> listPage(IPage<EmployeeInfo> page,@Param("name") String name);
}