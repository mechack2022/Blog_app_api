package com.fragile.blog_api.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String name;

    private String email;

    private String about;
}
