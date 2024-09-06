package com.example.demo4.config.phone;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class phone {
    public boolean isPhoneValid(String phone) {
        Pattern pattern = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
