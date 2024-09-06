package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.config.MD5Util;
import com.example.demo4.config.phone.phone;
import com.example.demo4.mapper.BgPlatformMenuInfoRoleDao;
import com.example.demo4.mapper.EmployeeDao;

import com.example.demo4.mapper.RoleDao;
import com.example.demo4.pojo.jo.EmployeeInfo;
import com.example.demo4.pojo.jo.page;
import com.example.demo4.pojo.po.BgPlatformMenuInfoRole;
import com.example.demo4.pojo.po.Employee;
import com.example.demo4.pojo.po.Role;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class EmployeeImp extends ServiceImpl<EmployeeDao, Employee>{
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BgPlatformMenuInfoRoleDao bgPlatformMenuInfoRoleDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private MD5Util md5Util;

    public void add(Employee employee){
        phone p = new phone();
        var phoneValid = p.isPhoneValid(employee.getEmployeePhone());
        if (phoneValid == false) throw new RuntimeException("手机号格式有误");
        if (employee.getRolePermission() == null)  throw new RuntimeException("角色不能为空");
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getEmployeePhone,employee.getEmployeePhone());
        var employee2 = employeeDao.selectOne(wrapper);
        if (employee2 != null){
            throw new RuntimeException("手机号重复");
        }

        var employee1 = new Employee();
        if(StringUtils.isNotBlank(employee.getPassword())){
            var s = md5Util.md5(employee.getPassword());
            employee1.setPassword(s);
        }
        employee1.setEmployeeName(employee.getEmployeeName());
        employee1.setEmployeePhone(employee.getEmployeePhone());
        employee1.setState(employee.getState());
        employee1.setCreationTime(LocalDateTime.now());
        if (employee.getRolePermission() == null){
            employee1.setRolePermission(0);
        }else {
            employee1.setRolePermission(employee.getRolePermission());
        }
        if (StringUtils.isNotBlank(employee.getAvatar())){
            employee1.setAvatar(employee.getAvatar());
        }

        employeeDao.insert(employee1);
    }

    public IPage<EmployeeInfo> get(String name, String phone, Integer pageNum, Integer pageSize){
        LambdaQueryWrapper<EmployeeInfo> wrapper = new LambdaQueryWrapper<>();
        if (name != null){
            wrapper.like(EmployeeInfo::getEmployeeName,name);
        }
        if (phone != null){
            wrapper.like(EmployeeInfo::getEmployeePhone,phone);
        }
        wrapper.orderByDesc(EmployeeInfo::getEmployeeId);
        IPage<EmployeeInfo> topage = new Page<>(pageNum,pageSize);
        var record2 = employeeDao.listPage(topage,name);
        record2.getRecords().forEach(v -> {
            if (v.getState() == 1){
                v.setStateName("启用");
            }else {
                v.setStateName("停用");
            }
        }); 
        return record2;
    }


    public EmployeeInfo getEmployeeInfo(Integer id){
        var employee = employeeDao.selectById(id);
        var employeeInfo = new EmployeeInfo();
        if (employee == null){
            throw new RuntimeException("员工不存在");
        }
        //复制
        BeanUtils.copyProperties(employee,employeeInfo);
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getRoleId,employee.getRolePermission());
        var role = roleDao.selectById(employee.getRolePermission());
        employeeInfo.setRoleName(role.getRoleName());
        employeeInfo.setRoleId(role.getRoleId());
        if (StringUtils.isNotBlank(employee.getAvatar())){
            employeeInfo.setAvatar(employee.getAvatar());
        }else {
            employeeInfo.setAvatar("");
        }

        return employeeInfo;
    }

    public void editEmployee(EmployeeInfo info){
        var employee = employeeDao.selectById(info.getEmployeeId());
        if (info.getEmployeeName() != null){
            employee.setEmployeeName(info.getEmployeeName());
        }
        if (info.getRolePermission() != null){
            employee.setRolePermission(info.getRolePermission());
        }
        if (info.getEmployeePhone() != null){
            employee.setEmployeePhone(info.getEmployeePhone());
        }
        if (info.getState() != null){
            employee.setState(info.getState());
        }
        var i = employeeDao.updateById(employee);

    }

}
