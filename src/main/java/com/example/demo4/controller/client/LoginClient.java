package com.example.demo4.controller.client;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo4.config.MD5Util;
import com.example.demo4.pojo.jo.Login;
import com.example.demo4.pojo.po.BgUsers;
import com.example.demo4.servers.UserImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/***
 * 登陆
 */
@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*",maxAge = 3600)
public class LoginClient {
    @Autowired
    private UserImp userImp;
    @Autowired
    private MD5Util mD5Util;
    private String num = "";
    /***
     * 客户端登陆
     * @param
     */
    @PostMapping("/loginClient")
    public BgUsers login(HttpServletRequest request, @RequestBody Login info){
        System.out.println(info.getUserName()+info.getPassword());
        LambdaQueryWrapper<BgUsers> waper = new LambdaQueryWrapper<>();
        if (StringUtils.isBlank(info.getUserName())) throw new IllegalArgumentException("用户名不能为空");
        if (StringUtils.isBlank(info.getPassword())) throw new IllegalArgumentException("密码不能为空");
        waper.eq(BgUsers::getUserName,info.getUserName());
        var user = userImp.getOne(waper);
        System.out.println(user);
        if (Objects.isNull(user)) throw new IllegalArgumentException("用户不存在");
        var password = mD5Util.md5(info.getPassword());
        if (user.getPassword().equals(password)){
            System.out.println("密码正确");
            num = num + "snq+";
            System.out.println(num);
            return user;
        }else {

            throw new IllegalArgumentException("密码错误");
        }

  }

}
