package com.example.demo4.controller;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.config.ExcelReader;
import com.example.demo4.config.MD5Util;
import com.example.demo4.config.document.Document;
import com.example.demo4.pojo.jo.*;
import com.example.demo4.pojo.po.*;
import com.example.demo4.servers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class BgUsersController {

    @Autowired
    private BgUsersGroupServers bgUsersGroupServers;
    @Autowired
    private EmployeeImp employeeImp;
    @Autowired
    private UserImp userImp;
    @Autowired
    private Document document;
    private int index = 0;
    @Autowired
    private BgUsersImpotLoseServers bgUsersImpotLoseServers;
    @Autowired
    private BgUserImpotRecordServers bgUserImpotRecordServers;
    @Autowired
    private MD5Util md5Util;

    /***
     * 添加学生
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add/user")
    public void addUser(@RequestBody BgUsers user, HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        if(StringUtils.isBlank(user.getUserName())) throw new IllegalArgumentException("用户名不能为空");
        if(StringUtils.isBlank(user.getPassword())) throw new IllegalArgumentException("密码不能为空");
        user.setPassword(md5Util.md5(user.getPassword()));
        Employee admin = (Employee) request.getAttribute("admin");
        user.setEmployeeId(admin.getEmployeeId());
        if (Objects.isNull(user.getGroupId())) throw new IllegalArgumentException("分组不能为空");
        user.setStuats(1);
        user.setCreateTime(LocalDateTime.now());
        userImp.save(user);
    }
    /***
     * 添加学生分组
     */
    @PostMapping("/add/userGroup")
    public void userGroup(@Valid @RequestBody BgUsersGroup usersGroup) {
        bgUsersGroupServers.addUserGroup(usersGroup);
    }
    /***
     * 查询学生分组
     * @return
     */
    @GetMapping("/get/userGroup")
    public HashMap<String,Object> getGroup(String name,Integer id) {
        System.out.println(id);
        HashMap<String,Object> map =new HashMap<>();
        LambdaQueryWrapper<BgUsers> wapper = new LambdaQueryWrapper<>();
        var list = userImp.list(wapper);
        map.put("total2",list.size());
        var collect = list.stream().filter(user -> user.getGroupId() == 1).count();
        map.put("defaultUserData",collect);
        var list1 = employeeImp.list(new LambdaQueryWrapper<Employee>());
        map.put("EmployeeList",list1);
        List<Map<String,Object>> usersGroups = new ArrayList<>();
        LambdaQueryWrapper<BgUsersGroup> wapper2 = new LambdaQueryWrapper<>();
        wapper2.orderByDesc(BgUsersGroup::getCreationTime);
        if (StringUtils.isNotBlank(name)){
            wapper2.like(BgUsersGroup::getGroupName,name);
        }
        var bgUsersGroups = bgUsersGroupServers.list(wapper2);
        var bgUsersGroupList1 = bgUsersGroups.stream().filter(grou -> grou.getParentId() == 0).collect(Collectors.toList());
        if(bgUsersGroupList1 == null) throw new RuntimeException("分组数据空了");
        bgUsersGroupList1.forEach(v -> {
            HashMap<String,Object> map1 =new HashMap<>();
            map1.put("id",v.getGroupId());
            map1.put("label",v.getGroupName());
            map1.put("groupLeve",v.getGroupLeve());
            map1.put("parentId",v.getParentId());
            List<Integer> groupIdList = new ArrayList<>();
            var bgUsersGroups1 = bgUsersGroupServers.list(new QueryWrapper<BgUsersGroup>().lambda().eq(BgUsersGroup::getParentId, v.getGroupId()));
            if (!bgUsersGroups1.isEmpty()){
                bgUsersGroups1.forEach(u -> {
                    groupIdList.add(u.getGroupId());
                });
            }
            if(!groupIdList.isEmpty()){
                LambdaQueryWrapper<BgUsersGroup> war1 = new LambdaQueryWrapper<>();
                war1.in(BgUsersGroup::getParentId,groupIdList);
                var bgUsersGroups2 = bgUsersGroupServers.list(war1);
                if (!bgUsersGroups2.isEmpty()){
                    bgUsersGroups2.forEach(y -> {
                        groupIdList.add(y.getGroupId());
                    });
                }
            }
            groupIdList.add(v.getGroupId());
            LambdaQueryWrapper<BgUsers> war = new LambdaQueryWrapper<>();
            war.in(BgUsers::getGroupId,groupIdList);
            var size = userImp.list(war).size();

            map1.put("count",size);
            List<Map<String,Object>> usersGroups2 = new ArrayList<>();
            var bgUsersGroupList2 = bgUsersGroups.stream().filter(c -> c.getParentId() == v.getGroupId()).collect(Collectors.toList());
            if (bgUsersGroupList2.size() >0){
                bgUsersGroupList2.forEach(k -> {
                    HashMap<String,Object> map2 =new HashMap<>();
                    map2.put("id",k.getGroupId());
                    map2.put("label",k.getGroupName());
                    map2.put("groupLeve",k.getGroupLeve());
                    map2.put("parentId",k.getParentId());
                    List<Map<String,Object>> usersGroups3 = new ArrayList<>();
                    var bgUsersGroupList3 = bgUsersGroups.stream().filter(c -> c.getParentId() == k.getGroupId()).collect(Collectors.toList());
                    if (bgUsersGroupList3.size() > 0 ){
                        bgUsersGroupList3.forEach(o -> {
                            HashMap<String,Object> map3 =new HashMap<>();
                            map3.put("id",o.getGroupId());
                            map3.put("label",o.getGroupName());
                            map3.put("groupLeve",o.getGroupLeve());
                            map3.put("parentId",o.getParentId());
                            usersGroups3.add(map3);
                            map2.put("children",usersGroups3);
                        });
                    }
                    usersGroups2.add(map2);
                });
                map1.put("children",usersGroups2);
            }
            usersGroups.add(map1);
        });
        map.put("list",usersGroups);
        return map;
    }
    /***
     * 测试枚举
     * @return
     */
    @GetMapping("/get/meiju")
    public void meiju() {
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Employee::getCreationTime,Employee::getEmployeeName,Employee::getEmployeeId);
        var list = employeeImp.list(wrapper);
        bgUsersGroupServers.getMapper();
    }
    /***
     * 删除学员分组
     * @return
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delet/userGroup/{id}")
    public void delGroup(@PathVariable Integer id){
        var count = userImp.count(new QueryWrapper<BgUsers>().lambda().eq(BgUsers::getGroupId, id));
        if (count > 0) throw new RuntimeException("有学员使用无法被删除");
        bgUsersGroupServers.removeById(id);
    }

    /***
     * 获取用户
     * @param searchname
     * @return
     */
    @GetMapping("get")
    public IPage<UserInfo> getUser(searchName searchname){
        return userImp.listUserInfo(searchname);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("del")
    public void delUser(@RequestParam Integer id){
        System.out.println(id);
        userImp.removeById(id);
    }

    @GetMapping("getUserInfo")
    public UserInfo getUserInfo(@RequestParam Integer id){
        var userInfo = new UserInfo();
        var byId = userImp.getById(id);
        if (Objects.isNull(byId)) throw new IllegalArgumentException("不能为空");
        BeanUtils.copyProperties(byId,userInfo);
        BeanUtils.copyProperties(bgUsersGroupServers.getById(byId.getGroupId()),userInfo);
        return userInfo;
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("eidt/user")
    public void eidtUser(@RequestBody BgUsers user){
        var update = userImp.updateById(user);
        if (!update) throw new IllegalArgumentException("用户空了");
    }
    @PostMapping("eidt/userGroup")
    public void eidtuserGroup(@RequestBody UserInfo info){
        var byId = bgUsersGroupServers.getById(info.getGroupId());
        byId.setGroupName(info.getGroupName());
        bgUsersGroupServers.updateById(byId);
    }
     /**
     * 处理导出表格的请求。
     * 通过POST请求访问该方法，用于将预定义的Excel模板以添加客户模版表的格式导出。
     *
     * @param response 用于设置响应头信息，包括字符编码、内容类型和文件名，以引导浏览器下载文件。
     */
    @PostMapping("ExportForm")
    public void ExportForm(HttpServletResponse response) {
    // 设置响应的字符编码为UTF-8，以确保文件名的正确显示。
    response.setCharacterEncoding("utf-8");
    // 构建ClassPathResource对象，指向模板文件在类路径中的位置。
    ClassPathResource classPathResource = new ClassPathResource("template/添加客户模版表-导出.xlsx");
    // 设置响应的内容类型，指定导出的文件是一个Excel 2007格式的表格。
    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    // 定义导出文件的名称。
    String fileName = "客户模版表";
    // 对文件名进行URL编码，以确保文件名中的特殊字符在下载时不会导致错误。
    // 同时将加号替换为%20，这是URL编码中的规范。
    String filename = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    // 构建Content-Disposition头，指定浏览器以附件形式下载文件，并设置文件名。
    // 使用utf-8编码和特定格式的filename*属性来确保文件名在各种环境下都能正确解析。
    String disposition = "attachment;filename*=utf-8''" + filename + ".xlsx";
    // 设置响应头的Content-Disposition属性，引导浏览器进行文件下载。
    response.setHeader("Content-Disposition", disposition);
    // 将模板文件写入响应流，完成文件导出。
    // 这里的document.putStream是一个假设存在的方法，用于将资源文件写入响应流。
    document.putStream(classPathResource, response);
    }
    /***
     * 单文件导入
     * @param file
     */

    @PostMapping("upload")
    public void upload(@RequestParam("file") MultipartFile file,
                       @RequestParam("selectedValue") Integer selectedValue,
                       @RequestParam("id") Integer id,
                       HttpServletRequest request
    )
    {
        // 创建Excel读取器
        ExcelReader excelReader = new ExcelReader();
        try {
            // 使用EasyExcel框架读取上传的文件，指定数据模型为UserImport，并注册回调处理器
            EasyExcel.read(file.getInputStream(), UserImport.class, excelReader).sheet().headRowNumber(2).doRead();
            // 获取解析后的用户数据列表
            List<UserImport> dataList = excelReader.getDataList();
            List<BgUsers> userList = new ArrayList<>();
            List<BgUsersImpotLose> errlist = new ArrayList<>();

            for (UserImport res:dataList){
                var bgUser = new BgUsers();
                if (StringUtils.isNotBlank(res.getUserName())){
                    bgUser.setUserName(res.getUserName());
                }
                if (StringUtils.isNotBlank(res.getPassword())){
                    bgUser.setPassword(res.getPassword());
                }
                bgUser.setStuats(1);
                bgUser.setEmployeeId(selectedValue);
                bgUser.setGroupId(id);
                if (StringUtils.isNotBlank(res.getPhone())){
                    bgUser.setPhone(res.getPhone());

                }
                if (StringUtils.isNotBlank(res.getCard())){
                    bgUser.setCard(res.getCard());
                }
                if (StringUtils.isNotBlank(res.getIsAuthentication())){
                    if (res.getIsAuthentication().equals("是")){
                        bgUser.setIsAuthentication(1);
                    }else {
                        bgUser.setIsAuthentication(0);
                    }
                }
                if (res.getSex().equals("男")){
                    bgUser.setSex(1);
                }else {
                    bgUser.setSex(2);
                }
                bgUser.setCreateTime(LocalDateTime.now());
                userList.add(bgUser);

                if (StringUtils.isBlank(res.getUserName()) || StringUtils.isBlank(res.getPassword()) || StringUtils.isBlank(res.getSex()) || StringUtils.isBlank(res.getIsAuthentication())) {
                    BgUsersImpotLose bgUsersImpotLose = new BgUsersImpotLose();
                    BeanUtils.copyProperties(res,bgUsersImpotLose);
                    if (StringUtils.isBlank(res.getUserName())) {
                        bgUsersImpotLose.setCause("用户名不能为空");
                    }
                    if (StringUtils.isBlank(res.getPassword())){
                        bgUsersImpotLose.setCause("密码不能为空");
                    }
                    if (StringUtils.isBlank(res.getSex())) {
                        bgUsersImpotLose.setCause("性别不能为空");
                    }
                    if (StringUtils.isBlank(res.getIsAuthentication())){
                        bgUsersImpotLose.setCause("是否实名不能为空");
                    }
                    errlist.add(bgUsersImpotLose);
                }

            }
            BgUserImpotRecord impotRecord = new BgUserImpotRecord();
            if(errlist.size() > 0){
                Random random = new Random();
                long randomNum = 10000000000L + random.nextInt(900000000);
                var listErr = errlist.stream().map(err -> {
                    err.setPresentId(randomNum);
                    return err;
                }).collect(Collectors.toList());
                impotRecord.setPresentId(randomNum);
                bgUsersImpotLoseServers.saveBatch(listErr);
            }
            var collect = userList.stream()
                    .filter(user -> StringUtils.isNotBlank(user.getPassword()))
                    .filter(user -> Objects.nonNull(user.getIsAuthentication()))
                    .collect(Collectors.toList());
            impotRecord.setLoseNum(errlist.size());
            impotRecord.setSucceedNum(collect.size());
            impotRecord.setImpotTime(LocalDateTime.now());
            bgUserImpotRecordServers.save(impotRecord);
            userImp.saveBatch(collect);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     * 批量删除
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delUserList")
    public void delUserList(String ids){
        String[] strArr = ids.split(",");
        List<Integer> intList = new ArrayList<>();
        for (String s : strArr) {
            intList.add(Integer.parseInt(s));
        }
        System.out.println(intList);
        userImp.removeBatchByIds(intList);
    }


    /***
     * 批量导出
     */
    @PostMapping("deriveUserList")
    public void deriveUserList(String ids,HttpServletResponse response){
        String[] strArr = ids.split(",");
        if (StringUtils.isBlank(ids)) throw new IllegalArgumentException("删除id不能为空");
        List<Integer> intList = new ArrayList<>();
        for (String s : strArr) {
            intList.add(Integer.parseInt(s));
        }
        System.out.println(intList);
        var bgUsers = userImp.listByIds(intList);
        List<UserInfo> exporList = new ArrayList<>();
        if (Objects.nonNull(bgUsers)){
            bgUsers.forEach(v -> {
                var userInfo = new UserInfo();
                BeanUtils.copyProperties(v,userInfo);
                userInfo.setIndex(index);
                exporList.add(userInfo);
                index += 1;
            });
        }
        for (UserInfo user:exporList){
            String sex2 = user.getSex() == 1 ? "男" : "女";
            user.setSex2(sex2);
            String authentication = user.getIsAuthentication() == 1 ? "是" : "否";
            user.setAuthentication(authentication);
        }
        System.out.println(exporList);
        ClassPathResource classPathResource = new ClassPathResource("template/用户管理-导出.xlsx");

        String fileName = "用户管理-导出";
        try {
            preExportExcel(response, fileName);
            EasyExcel.write(response.getOutputStream())
                    .withTemplate(classPathResource.getInputStream())
                    .sheet()
                    .doFill(() -> {
                        // 分页查询数据
                        return exporList;
                    });
        } catch (IOException e) {
            System.out.println(e);

        }

    }
    private void preExportExcel(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        String filename = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        String disposition = "attachment;filename*=utf-8''" + filename + ".xlsx";
        response.setHeader("Content-Disposition", disposition);
    }
    @GetMapping("getUserImportRecord")
    public IPage<BgUserImpotRecord> getUserImportRecord(){
        var userRecord = bgUserImpotRecordServers.getUserRecord();
        return userRecord;
    }
    @PostMapping("exportFailureRecord")
    public void exportFailureRecord(HttpServletResponse response,Long presentId){
        System.out.println(presentId);
        List<UserImport> exporList = new ArrayList<>();
        if (Objects.nonNull(presentId)){
            LambdaQueryWrapper<BgUsersImpotLose> wapper = new LambdaQueryWrapper<>();
            wapper.eq(BgUsersImpotLose::getPresentId,presentId);
            var list = bgUsersImpotLoseServers.list(wapper);
            list.forEach(lose -> {
                var userInfo = new UserImport();
                BeanUtils.copyProperties(lose,userInfo);
                userInfo.setIsAuthentication(lose.getIsAuthentication());
                userInfo.setSex(lose.getSex());
                exporList.add(userInfo);
            });
        }
        bgUsersImpotLoseServers.inResponse(response,exporList);

    }
}