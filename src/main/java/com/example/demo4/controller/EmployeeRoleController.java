package com.example.demo4.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo4.config.JsonUtils;
import com.example.demo4.config.MD5Util;
import com.example.demo4.config.OSSConfig;
import com.example.demo4.config.ResponseUtils;
import com.example.demo4.config.document.fileOperations;
import com.example.demo4.config.phone.phone;
import com.example.demo4.pojo.jo.EmployeeInfo;
import com.example.demo4.pojo.jo.Result;
import com.example.demo4.pojo.jo.roleInfo;
import com.example.demo4.pojo.po.*;
import com.example.demo4.servers.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
/***
 * 员工角色
 */
@RestController
@RequestMapping("/employeeRoleControllerme")

public class EmployeeRoleController {

    @Autowired
    private com.example.demo4.config.document.fileOperations fileOperations;
    @Autowired
    private OSSConfig ossConfig;
    @Autowired
    private EmployeeImp employeeImp;
    @Autowired
    private BgPlatformMenuInfoImp bgPlatformMenuInfoImp;
    @Autowired
    private RoleImp roleImp;
    public static String roleId;
    @Autowired
    private BgPlatformMenuInfoRoleImp bgPlatformMenuInfoRoleImp;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private UserImp userImp;
    /***
     * 添加员工nano终端添加的
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addEmployee(@Valid @RequestBody Employee employee) {
        employeeImp.add(employee);
    }
    /***
     * 获取员工信息
     */
    @GetMapping("/get")
    public IPage<EmployeeInfo> getEmployee(String name, String phone, Integer pageNum, Integer pageSize) {
        System.out.println(name);
        return employeeImp.get(name,phone,pageNum,pageSize);
    }
    /***
     * 员工数据回写
     */
    @GetMapping("/edit/WriteBack")
    public EmployeeInfo editEmployee(Integer id) {
        System.out.println(id);
        var employeeInfo = employeeImp.getEmployeeInfo(id);
        return employeeInfo;
    }

    /***
     * 员工编辑数据
     */
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    @PostMapping("/edit")
    public void edit(@RequestBody EmployeeInfo info) {
        phone p = new phone();
        var phoneValid = p.isPhoneValid(info.getEmployeePhone());
        if (phoneValid == false) throw new RuntimeException("手机号格式有误");
        var employee = new Employee();
        if (StringUtils.isNotBlank(info.getPassword())){
            BeanUtils.copyProperties(info,employee);
            var s = md5Util.md5(employee.getPassword());
            employee.setPassword(s);
        }else {
            employee.setEmployeeId(info.getEmployeeId());
            employee.setEmployeeName(info.getEmployeeName());
            employee.setEmployeePhone(info.getEmployeePhone());
            employee.setRolePermission(info.getRolePermission());
            employee.setState(info.getState());
            employee.setAvatar(info.getAvatar());
        }
        employeeImp.updateById(employee);
    }

    /***
     * 获取菜单数据
     */
    @GetMapping("/getMenuInfo")
    public Result getMenuInfo() {
        var bgPlatformMenuInfo = bgPlatformMenuInfoImp.getBgPlatformMenuInfo();
        return ResponseUtils.stateList(bgPlatformMenuInfo);
    }
    /***
     * 角色绑定权限
     */
    @PostMapping("/bindingPermission")
    public void bindingPermission(@RequestBody List<Map<String, Object>> mapData) {
        List<Map<String,Object>> objects = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        mapData.forEach(v -> {
            var vall1 = v.containsKey("vall");
            if(vall1){
                roleId = v.get("vall").toString();
            }
            try {
                var s = objectMapper.writeValueAsString(v);
                boolean b = false;
                try {
                    b = JsonUtils.hasProperty(s,"children");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (b){
                    objects.add(v);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        var bgPlatformMenuInfoRoles = bgPlatformMenuInfoRoleImp.BgPlatformMenuInfoRoleList(mapData,roleId);
        System.out.println(bgPlatformMenuInfoRoles);
        bgPlatformMenuInfoRoles.forEach(c -> {
            LambdaQueryWrapper<BgPlatformMenuInfoRole> wapeer = new LambdaQueryWrapper<>();
            wapeer.eq(BgPlatformMenuInfoRole::getRoleid,roleId);
            var list = bgPlatformMenuInfoRoleImp.list(wapeer);
            if (list != null){
                var collect = list.stream().map(v -> v.getId()).collect(Collectors.toList());
                bgPlatformMenuInfoRoleImp.removeByIds(collect);
            }
        });
        bgPlatformMenuInfoRoleImp.saveBatch(bgPlatformMenuInfoRoles);



    }
    /***
     * 添加角色
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/addRole")
    public void addRole(@Valid @RequestBody Role role) {
        if (StringUtils.isBlank(role.getRoleName())) throw new IllegalArgumentException("角色名称不能为空");
        roleImp.addRole(role);

    }
    /***
     * 角色列表
     */
    @GetMapping("/getRole")
    public List<Role> getRole() {
        var roleList = roleImp.findRole();
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Role::getRoleId);
        var list = roleImp.list(wrapper);
        return list;
    }

    /***
     * 角色详情
     */
    @GetMapping("/getRoleDetails")
    public roleInfo getRoleDetails(Integer id) {
        roleInfo Info = new roleInfo();
        var byId = roleImp.getById(id);
        BeanUtils.copyProperties(byId,Info);
        System.out.println(byId);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getRolePermission,id);
        var count = employeeImp.count(wrapper);
        Info.setCunt(count);
        LambdaQueryWrapper<BgPlatformMenuInfoRole> wrapper1 = new LambdaQueryWrapper<>();
        wrapper1.eq(BgPlatformMenuInfoRole::getRoleid,id);
        var list = bgPlatformMenuInfoRoleImp.list(wrapper1);
        var collect = list.stream().map(v -> v.getBgPlatformMenuInfo()).collect(Collectors.toList());
        Info.setIds(collect);
        return Info;
    }
    /***
     * 删除角色
     * @param id
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/delRole")
    public void delRole(Integer id) {
        LambdaQueryWrapper<Employee> warpper = new LambdaQueryWrapper<>();
        warpper.eq(Employee::getRolePermission,id);
        var count = employeeImp.count(warpper);
        if (count > 0) throw new IllegalArgumentException("已经关联员工不能删除");
        roleImp.delRole(id);
    }

    /***
     * 删除员工数据
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/delet")
    public void edit(Integer id) {
        LambdaQueryWrapper<BgUsers> warr = new LambdaQueryWrapper<>();
        warr.eq(BgUsers::getEmployeeId,id);
        var list = userImp.list(warr);
        if (list.size() == 0){
            employeeImp.removeById(id);
        }else {
            throw new IllegalArgumentException("管理员关联用户，用户请先换管理员");
        }

    }
    /***
     * 上传图片
     */
    @PostMapping("/upload")
    public HashMap<String,Object> upload(MultipartFile file) {
        return fileOperations.upload(file);
    }

    @GetMapping("getInfo")
        public HashMap<String,Object> getInfo(HttpServletRequest request){
        HashMap<String,Object> map =  new HashMap<>();
        var admin =(Employee) request.getAttribute("admin");
        var employee = employeeImp.getById(admin.getEmployeeId());
        map.put("employeeInfo",employee);
        return map;
    }

}
