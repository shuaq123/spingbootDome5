package com.example.demo4.controller.DishesManagementController;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo4.enumeration.questionType;
import com.example.demo4.mapper.BgDishesDao;
import com.example.demo4.pojo.jo.Search;
import com.example.demo4.pojo.po.BgBasicQuestions;
import com.example.demo4.pojo.po.BgDishes;
import com.example.demo4.pojo.po.BgQuestionsGroup;
import com.example.demo4.pojo.po.Employee;
import com.example.demo4.servers.BgBasicQuestionsServer;
import com.example.demo4.servers.BgDishesSercers;
import com.example.demo4.servers.BgQuestionsGroupServses;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/dishes")
public class DishesManagement {
    @Autowired
    private BgBasicQuestionsServer bgBasicQuestionsServer;
    @Autowired
    private BgQuestionsGroupServses bgQuestionsGroupServses;
    @Autowired
    private BgDishesDao bgDishesDao;
    @Autowired
    private BgDishesSercers bgDishesSercers;
    @Autowired
    private com.example.demo4.config.document.fileOperations fileOperations;
    @PostMapping("/upload")
    public HashMap<String,Object> upload(MultipartFile file){
        System.out.println("上传图片");
       return fileOperations.upload(file);
    }
    @PostMapping("/addDishes")
    public void addDishes(@RequestBody BgDishes dishes, HttpServletRequest request){
        var admin =(Employee) request.getAttribute("admin");
        System.out.println(admin);
        dishes.setAdminId(admin.getEmployeeId());
        bgDishesSercers.save(dishes);
    }
    @GetMapping("/get")
    public IPage<BgDishes> getDishes(Integer currentPage,Integer pageSize){
        IPage<BgDishes> topage = new Page<>(currentPage,pageSize);
        var bgUserImpotRecordIPage = bgDishesDao.selectne(topage);
        return bgUserImpotRecordIPage;
    }
    @DeleteMapping("/del")
    public void delDishes(String ids,HttpServletRequest request){
        var admin =(Employee) request.getAttribute("admin");
        //操作删除的管理员
        var employeeId = admin.getEmployeeId();
        String[] idlist =ids.split(",");
        List<String> sList = Arrays.asList(idlist);
        var bgDishes = bgDishesSercers.listByIds(sList);
        //有权限删除的菜单
        var bgDishes1 = bgDishes.stream().filter(s -> s.getAdminId().equals(employeeId))
                .map(BgDishes::getMenuId)
                .collect(Collectors.toList());
        if (bgDishes1.size()>0){
            bgDishesDao.deleteBatchIds(bgDishes1);
        }else {
            throw new IllegalArgumentException("非本管理创建，没有能删除的数据");
        }
    }
    @PostMapping("/add")
    public void addQuestionGroup(@RequestBody BgQuestionsGroup info){
        info.setCreationTime(LocalDateTime.now());
        bgQuestionsGroupServses.save(info);
    }
    @GetMapping("/getGroup")
    public HashMap<String,Object> getQuestionGroup(@Param("name") String name){
        HashMap<String,Object> map =  new HashMap<>();
        List<HashMap<String,Object>> list = new ArrayList<>();
        LambdaQueryWrapper<BgQuestionsGroup> war = new LambdaQueryWrapper<>();
        war.like(BgQuestionsGroup::getGroupName,name);
        var listGoup = bgQuestionsGroupServses.list(war);
        listGoup.stream().filter(goup -> goup.getLeve() == 1 ).forEach(c -> {
            HashMap<String,Object> childrenMap =  new HashMap<>();
            childrenMap.put("label",c.getGroupName());
            childrenMap.put("id",c.getGroupId().toString());
            List<HashMap<String,Object>> listB = new ArrayList<>();
            listGoup.stream().filter(k -> k.getLeve() == 2 && k.getPrentId().toString().equals(c.getGroupId().toString())).forEach(i -> {
                HashMap<String,Object> mapB =  new HashMap<>();
                mapB.put("label",i.getGroupName());
                mapB.put("id",i.getGroupId().toString());
                listB.add(mapB);
            });
            childrenMap.put("children",listB);
            list.add(childrenMap);
        });
        map.put("list",list);
        return map;
    }

    @DeleteMapping("/delQuestionGroup")
    public void delQuestionGroup(@RequestParam String id){
        System.out.println(id);
        LambdaQueryWrapper<BgBasicQuestions> war = new LambdaQueryWrapper<>();
        war.eq(BgBasicQuestions::getQuestionsClass,id);
        var list = bgBasicQuestionsServer.list(war);

        if (list.size()>0){
            throw new IllegalArgumentException("有关联题目无法直接删除");
        }
        bgQuestionsGroupServses.removeById(Long.valueOf(id));
    }

    /***
     * 添加题目
     * @param info
     */
    @PostMapping("/addQuestion")
    public void addQuestion(@RequestBody BgBasicQuestions info){
        System.out.println(info.getId());
        if (info.getId() == null){
            var equals = Objects.equals(info.getQuestiontype(), questionType.QUESTIONSANSWERS);
            System.out.println(equals);
            info.setType(info.getQuestiontype().getType());
            info.setCreationTime(LocalDateTime.now());
            info.setDeletedFlag(0);
            var byId = bgQuestionsGroupServses.getById(info.getQuestionsClass());
            if (Objects.isNull(byId)) throw new IllegalArgumentException("分组已被删除");
            bgBasicQuestionsServer.save(info);
        }else {
            var byId = bgBasicQuestionsServer.getById(info.getId());
            if (Objects.nonNull(byId)){
                info.setQuestionsId(byId.getQuestionsId());
                bgBasicQuestionsServer.updateById(info);
            }

        }

    }
    @GetMapping("/getQuestion")
    public IPage<BgBasicQuestions> getQuestion(Search info){
       return bgBasicQuestionsServer.findQuestion(info);
    }
    @DeleteMapping("/delQuestion")
    public void delQuestion(@RequestParam String ids){
        // 使用逗号分隔符拆分字符串
        String[] idsArray = ids.split(",");
        // 将数组转换为列表
        List<String> idsList = Arrays.asList(idsArray);
        bgBasicQuestionsServer.removeBatchByIds(idsList);
    }
    @GetMapping("/QuestionInfo")
    public BgBasicQuestions QuestionInfo(String id){
        System.out.println(id);
        var byId = bgBasicQuestionsServer.getById(id);
        var byId1 = bgQuestionsGroupServses.getById(byId.getQuestionsClass());
            byId.setGroupName(byId1.getGroupName());
            byId.setQuestionsClassSting(byId1.getGroupId().toString());
        return byId;
    }
}
