package com.fragile.blog_api.payloads;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull
    @Size(min = 4, message = "username must be 4 character and above")
    private String name;

    @NotNull
    @Email(message = "email not valid!")
    private String email;


    @NotEmpty
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,}$", message="password must be t least 4 with one number and a letter")  //the regular expression specified ensures that the password must contain at least 8 characters, one letter and one number.
    private String password;

    @NotEmpty(message = "about can not be null")
    private String about;
}
