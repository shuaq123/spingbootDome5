package com.example.demo4.pojo.jo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) //链式写法
public class Login {
    private String userName;
    private String password;
}
