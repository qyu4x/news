package com.suhshine.schoolnews.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "school_news")
public class SchoolNews {

    @Id
    private String id;

    @NotBlank
    private String title;

    @NotBlank
    @Lob
    private String content;

    @NotBlank
    private String author;

    private Integer counter = 0;

    @OneToMany(mappedBy = "schoolNews", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comment = new HashSet<>();

    private LocalDateTime createdAt;

}
