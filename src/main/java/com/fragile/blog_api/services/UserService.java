package com.fragile.blog_api.services;

import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.payloads.UserResponseDto;

import java.util.List;

public interface UserService {


    UserDto  registerNewUser(UserDto user);
    UserResponseDto createUser(UserDto user);

    UserResponseDto updateUser(UserDto user, Integer id);

    UserResponseDto getUserById(Integer id);

    List<UserResponseDto> getAllUsers();

    void deleteUser(Integer id);

}
