package com.fragile.blog_api.payloads;

import com.fragile.blog_api.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Comment> comments = new HashSet<>();
}
