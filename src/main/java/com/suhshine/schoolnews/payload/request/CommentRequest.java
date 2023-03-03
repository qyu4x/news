package com.suhshine.schoolnews.payload.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "full name can't be empty")
    private String fullName;

    @Email(message = "type content must be email")
    @NotBlank(message = "email can't be empty")
    private String email;

    @NotBlank(message = "comment can't be empty")
    private String content;


}
