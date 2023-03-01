package com.fragile.blog_api.payloads;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class RoleDto {
    private Integer id;

    private String name;
}
