package com.example.demo4.servers;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo4.mapper.BgPlatformMenuInfoDao;
import com.example.demo4.mapper.BgPlatformMenuInfoRoleDao;
import com.example.demo4.mapper.EmployeeDao;
import com.example.demo4.mapper.RoleDao;
import com.example.demo4.pojo.po.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleImp extends ServiceImpl<RoleDao, Role> {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private BgPlatformMenuInfoRoleDao bgPlatformMenuInfoRoleDao;
    @Autowired
    private BgPlatformMenuInfoDao bgPlatformMenuInfoDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public void addRole(Role role){
        var role1 = new Role();
        role1.setRoleName(role.getRoleName());
        role1.setAdministrativeAuthority(role.getAdministrativeAuthority());
        role1.setRoleDescription(role.getRoleDescription());
        System.out.println(role1);
        roleDao.insert(role1);
    }

    public List<Role> findRole(){
        LambdaQueryWrapper<Role> war = new LambdaQueryWrapper<>();
        var roles = roleDao.selectList(war);
//        roles.forEach(role -> {
//            ObjectMapper objectMapper = new ObjectMapper();
//            List<Object> jsonList = null;
//            try {
//                jsonList = objectMapper.readValue(role.getJsonData(), List.class);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//            role.setList(jsonList);
//        });
        return roles;
    }

    @Transactional
    public void delRole(Integer id){

        var i = roleDao.deleteById(id);
        LambdaQueryWrapper<BgPlatformMenuInfoRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BgPlatformMenuInfoRole::getRoleid,id);
        var delete = bgPlatformMenuInfoRoleDao.delete(wrapper);
    }

}
