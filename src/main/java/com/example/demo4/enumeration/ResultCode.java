package com.example.demo4.enumeration;


public enum ResultCode {
    SUCCESS("请求成功",200),
    PARAM_ERROR("参数错误",400),
    UNAUTHORIZED( "未授权",404),
    FORBIDDEN("禁止访问",500);
    private String message;
    private Integer code;
    ResultCode(String message,Integer code){
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    public Integer getCode() {
        return code;
    }
}