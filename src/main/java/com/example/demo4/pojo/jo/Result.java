package com.example.demo4.pojo.jo;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {

    private int code;
    private String message;
    private T data;
    private List<?> list;
    // 构造方法、getters和setters省略
}