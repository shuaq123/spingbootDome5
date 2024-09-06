package com.example.demo4.config.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo4.config.AppContextHolder;
import com.example.demo4.pojo.po.Employee;
import com.example.demo4.servers.EmployeeImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private EmployeeImp employeeImp;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String method = request.getRequestURI();
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            response.setStatus(401);
            return false;
        } else {
            var userInfo = new JwtUtils().decodeToken(token);
            var userInfo1 = redisTemplate.opsForValue().get(userInfo);
            if (Objects.isNull(userInfo1)) throw new IllegalArgumentException("请先登陆");
            if (userInfo1.equals(token)) {
                LambdaQueryWrapper<Employee> war = new LambdaQueryWrapper<>();
                war.eq(Employee::getEmployeeName,userInfo);
                war.or();
                war.eq(Employee::getEmployeePhone,userInfo);
                var emp = employeeImp.list(war);
                if (Objects.isNull(emp)) return false;
                HashMap<String,Object> map = new HashMap<>();
                request.setAttribute("admin",emp.get(0));
                return true;
            } else {
                response.setStatus(401);
                return false;

            }

        }
    }
}