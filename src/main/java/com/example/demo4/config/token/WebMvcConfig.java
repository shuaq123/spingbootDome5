package com.example.demo4.config.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
    public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/images/**")
                .excludePathPatterns("/**/employee/**")
                .excludePathPatterns("/dome/**")
                .excludePathPatterns("/**/client/**")
                .excludePathPatterns("/**/error");

    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域的路径
//                .allowedOriginPatterns("*")  // 允许跨域的源
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许请求方法
                .maxAge(168000)  // 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(false);  // 是否发送cookie
    }
    //自定义目录
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/images/");
    }
}