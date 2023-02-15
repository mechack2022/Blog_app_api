package com.fragile.blog_api.controllers;


import com.fragile.blog_api.payloads.ApiResponse;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.payloads.UserResponseDto;
import com.fragile.blog_api.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserDto userDto = new UserDto();
        userDto.setName("testuser");
        userDto.setEmail("testuser@example.com");
        userDto.setPassword("password123");
        userDto.setAbout("test about");

        UserResponseDto createdUserResponseDto = new UserResponseDto();
        createdUserResponseDto.setId(1);
        createdUserResponseDto.setName(userDto.getName());
        createdUserResponseDto.setEmail(userDto.getEmail());
        createdUserResponseDto.setAbout(userDto.getAbout());

        when(userService.createUser(userDto)).thenReturn(createdUserResponseDto);

        ResponseEntity<UserResponseDto> responseEntity = userController.createUser(userDto);

        verify(userService, times(1)).createUser(userDto);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdUserResponseDto, responseEntity.getBody());
    }

    @Test
    public void testGetSingleUser() {
        Integer userId = 1;

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userId);
        userResponseDto.setName("testuser");
        userResponseDto.setEmail("testuser@example.com");
        userResponseDto.setAbout("test about");

        when(userService.getUserById(userId)).thenReturn(userResponseDto);

        ResponseEntity<UserResponseDto> responseEntity = userController.getSingleUser(userId);

        verify(userService, times(1)).getUserById(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponseDto, responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() {
        Integer userId = 1;

        UserDto userDto = new UserDto();
        userDto.setName("testuser_updated");
        userDto.setEmail("testuser_updated@example.com");
        userDto.setPassword("password123_updated");
        userDto.setAbout("test about_updated");

        UserResponseDto updatedUserResponseDto = new UserResponseDto();
        updatedUserResponseDto.setId(userId);
        updatedUserResponseDto.setName(userDto.getName());
        updatedUserResponseDto.setEmail(userDto.getEmail());
        updatedUserResponseDto.setAbout(userDto.getAbout());

        when(userService.updateUser(userDto, userId)).thenReturn(updatedUserResponseDto);

        ResponseEntity<UserResponseDto> responseEntity = userController.updateUserDto(userDto, userId);

        verify(userService, times(1)).updateUser(userDto, userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedUserResponseDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 1;

        ApiResponse apiResponse = new ApiResponse("user deleted successfully", true);

        ResponseEntity<ApiResponse> responseEntity = userController.deleteUser(userId);

        verify(userService, times(1)).deleteUser(userId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(apiResponse, responseEntity.getBody());
    }
}
