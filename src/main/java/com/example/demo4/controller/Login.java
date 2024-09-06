package com.example.demo4.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo4.config.AppContextHolder;
import com.example.demo4.config.ImageUtil.ImageUtil;
import com.example.demo4.config.MD5Util;

import com.example.demo4.config.token.JwtUtils;
import com.example.demo4.pojo.jo.loginJo;
import com.example.demo4.pojo.po.Employee;
import com.example.demo4.servers.EmployeeImp;
import org.apache.poi.ss.formula.functions.NumericFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

/***
 * 登陆
 */
@RestController
@RequestMapping("/employee")

public class Login {
    @Autowired
    private EmployeeImp employeeImp;
    @Autowired
    private MD5Util md5Util;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ImageUtil imageUtil;
    /***
     * 后台登陆
     * @param loginInfo
     */
    @PostMapping("/login")
    public String login(HttpServletRequest request,@RequestBody loginJo loginInfo){
        redisTemplate.delete(loginInfo.getUsername());
        LambdaQueryWrapper<Employee> wapeer = new LambdaQueryWrapper<>();
        wapeer.eq(Employee::getEmployeePhone,loginInfo.getUsername());
        wapeer.or();
        wapeer.eq(Employee::getEmployeeName,loginInfo.getUsername());
        var one = employeeImp.list(wapeer);

        if (one == null || one.size() == 0) throw new IllegalArgumentException("用户名/手机号不存在");
        if (one.get(0).getState() != 1 ) throw new IllegalArgumentException("账号已经停用");
        if (StringUtils.isBlank( loginInfo.getPassword() )) throw new IllegalArgumentException("密码不能为空");
        var s = md5Util.md5(loginInfo.getPassword());
        if ( !one.get(0).getPassword().equals(s)){
            throw new IllegalArgumentException("密码错误！");
        }

        var token = new JwtUtils().generateToken(loginInfo.getUsername());
//        var bean = AppContextHolder.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set(loginInfo.getUsername(),token);

        return token;
    }
    /**
     * @param @return 参数说明
     * @return BaseRestResult 返回类型
     * @Description: 生成滑块拼图验证码
     */
    @RequestMapping(value = "/getImageVerifyCode", method = RequestMethod.GET, produces = {"application/json;charset=UTF-8"})
    public Object getImageVerifyCode(String user) {

        Map<String, Object> resultMap = new HashMap<>();
        //滑块图片
        ImageUtil.createImage("https://huahanonlineppt.oss-cn-shenzhen.aliyuncs.com/uniappimage/11.png",resultMap);

        redisTemplate.opsForValue().set(user,resultMap.get("xWidth"));

        resultMap.remove("xWidth");

        return resultMap;
    }

    /***
     * 验证图形验证码
     */
    @GetMapping("/verification")
    public HashMap<String,Object> verification(String user,Integer movePercent){
        var xWidth = redisTemplate.opsForValue().get(user);
        System.out.println(xWidth);
        var integer = Integer.valueOf(xWidth.toString());
        int maxInteger = integer + 10;
        int minInteger = integer - 10;
        HashMap<String,Object> map = new HashMap<>();
        if (movePercent >= minInteger && movePercent<=maxInteger){
            map.put("data","验证成功");
        }else {
            throw new IllegalArgumentException("验证失败!!！");
        }
        return map;


    }


}
