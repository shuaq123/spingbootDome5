package com.example.demo4.config;

import com.example.demo4.pojo.jo.Result;

import java.util.List;

public class ResponseUtils {

    public static <T> Result<List<T>> success(List<T> data) {
        Result<List<T>> result = new Result<>();
        result.setCode(200);
        result.setMessage("success");
        result.setData(data);
        return result;
    }
    public static Result<?> error(int code, String message) {
        Result<?> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result<?> state(String data) {
        Result result = new Result<>();
        result.setCode(200);
        result.setMessage(data);
        return result;
    }

    public static Result<?> stateList(List<?> list) {
        Result result = new Result<>();
        result.setCode(200);
        result.setList(list);
        return result;
    }
}