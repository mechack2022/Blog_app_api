package com.fragile.blog_api.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private int postId;
    private String title;

    private String content;
    private String postImage;

    private Date addedDate;

    private CategoryDto category;

    private UserDto user;
}
