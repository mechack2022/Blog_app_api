package com.fragile.blog_api.event;

import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.payloads.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class UserCreationEvent extends ApplicationEvent {

    private UserDto user;

    private String message;

    public UserCreationEvent(UserDto user, String message) {
        super(user);
        this.user = user;
        this.message = message;
    }
}
