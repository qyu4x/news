package com.suhshine.schoolnews.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class SchoolNewsResponse {

    private String id;

    private String title;

    private String imageUrl;

    private String content;

    private String author;

    private LocalDate uploadDate;

    private LocalTime uploadHours;


}
