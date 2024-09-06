package com.example.demo4;
import com.example.demo4.config.CustomBanner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.demo4.mapper")
@EnableScheduling
public class Demo4Application {
    public static void main(String[] args) {
        SpringApplication applicationContext = new SpringApplication(Demo4Application.class);
        applicationContext.setBanner(new CustomBanner());
        applicationContext.run(args);
    }
}
