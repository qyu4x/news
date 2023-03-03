package com.suhshine.schoolnews.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SchoolNewsRequest {

    @NotBlank(message = "news title can't be empty")
    private String title;

    @NotBlank(message = "image url can't be empty")
    private String imageUrl;

    @NotBlank(message = "content can't be empty")
    private String content;

    @NotBlank(message = "author can't be empty")
    private String author;

}
