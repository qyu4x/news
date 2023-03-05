package com.suhshine.schoolnews.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CommentResponse {

    private String id;

    private String fullName;

    private String email;

    private String content;

    private LocalDate uploadDate;

    private LocalTime uploadHours;

}
