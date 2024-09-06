package com.example.demo4.config;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class AppContextHolder {

    private static ApplicationContext applicationContext;

    public AppContextHolder() {
    }
    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (Objects.isNull(AppContextHolder.applicationContext)) {
            AppContextHolder.applicationContext = applicationContext;
        }
    }
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name,requiredType);
    }

    @Nullable
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return Objects.isNull(servletRequestAttributes) ? null : servletRequestAttributes.getRequest();
    }


}
