package com.example.demo4.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Test {


    ENTRY_ING("报名中"),
    COMPOSE_ING("正在编场"),
    COMPOSE_END("编场结束");

    private String description;

}
