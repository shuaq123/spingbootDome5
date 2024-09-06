package com.example.demo4.pojo.jo;

import com.example.demo4.enumeration.Just;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Search {

    private Integer collection;
    private Integer currentPage;
    private Integer pageSize;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime entTime;
    private String search;
    private Just just;
    private Long groupID;
    private Integer optionelection;
}
