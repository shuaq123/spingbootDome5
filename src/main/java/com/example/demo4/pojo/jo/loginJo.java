package com.example.demo4.pojo.jo;

import lombok.Data;

import java.io.Serializable;

@Data
public class loginJo implements Serializable {
    private String username;
    private String password;

}
