package com.fragile.blog_api.event;

import com.fragile.blog_api.payloads.PostDto;
import com.fragile.blog_api.payloads.UserDto;
import lombok.*;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class PostCreationEvent  extends ApplicationEvent {

    public Integer userId;
    private PostDto post;
    private String title;


    public PostCreationEvent(PostDto post, Integer userId) {
        super(post);
        this.post = post;
        this.userId = userId;
    }
}

