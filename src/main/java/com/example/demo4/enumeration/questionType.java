package com.example.demo4.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum questionType {

    SINGLE(1,"单选题"),
    MANY(2,"多选题"),
    CORRECT(3,"判断题"),
    GAPFILLING(4,"填空题"),
    QUESTIONSANSWERS(5,"问答题");

    private Integer type;
    private String message;

}
