package com.example.demo4.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Just {

    COLLECTING(1,"采集中"),
    STOPCOLLECTING(2,"停止采集");
    private Integer type;
    private String message;

}
