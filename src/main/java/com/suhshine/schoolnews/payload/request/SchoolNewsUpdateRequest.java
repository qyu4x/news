package com.suhshine.schoolnews.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
public class SchoolNewsUpdateRequest {

    @NotBlank(message = "title can't be empty")
    private String title;

    @NotBlank(message = "content can't be empty")
    private String content;

    @NotBlank(message = "image url can't be empty")
    @URL(message = "image url must be a link")
    private String imageUrl;


}
