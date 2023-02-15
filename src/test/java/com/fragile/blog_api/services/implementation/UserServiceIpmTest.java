package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.payloads.UserResponseDto;
import com.fragile.blog_api.repositories.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceIpmTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceIpm userService;

    private UserDto userDto;
    private User user;
    private UserResponseDto userResponseDto;

    @Before
    public void setUp() {
        userDto = new UserDto();
        userDto.setName("John");
        userDto.setEmail("john@example.com");
        userDto.setPassword("Passw0rd");
        userDto.setAbout("A brief description");

        user = new User();
        user.setName("John");
        user.setEmail("john@example.com");
        user.setPassword("Passw0rd");
        user.setAbout("A brief description");

        userResponseDto = new UserResponseDto();
        userResponseDto.setId(1);
        userResponseDto.setName("John");
        userResponseDto.setEmail("john@example.com");
        userResponseDto.setAbout("A brief description");

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(modelMapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);
    }

    @Test
    public void testCreateUser() {
        when(userRepo.save(user)).thenReturn(user);
        UserResponseDto result = userService.createUser(userDto);
        assertEquals(userResponseDto, result);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(userRepo.save(user)).thenReturn(user);
        UserDto updatedUserDto = new UserDto();
        updatedUserDto.setName("John Doe");
        updatedUserDto.setEmail("johndoe@example.com");
        updatedUserDto.setPassword("newPassword");
        updatedUserDto.setAbout("A new description");

        UserResponseDto expected = new UserResponseDto();
        expected.setId(1);
        expected.setName("John Doe");
        expected.setEmail("johndoe@example.com");
        expected.setAbout("A new description");

        UserResponseDto result = userService.updateUser(updatedUserDto, 1);
        assertEquals(expected, result);
        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        UserResponseDto result = userService.getUserById(1);
        assertEquals(userResponseDto, result);
        verify(userRepo, times(1)).findById(1);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(user);
        when(userRepo.findAll()).thenReturn(users);
        List<UserResponseDto> expected = new ArrayList<>();
        expected.add(userResponseDto);
        List<UserResponseDto> result = userService.getAllUsers();
        assertEquals(expected, result);
        verify(userRepo, times(1)).findAll();
    }

    @Test
    public void testDeleteUser() {
        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        userService.deleteUser(1);
        verify(userRepo, times(1)).findById(1);
        verify(userRepo, times(1)).delete(user);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetUserByIdThrowsException() {
        when(userRepo.findById(2)).thenReturn(Optional.empty());
        userService.getUserById(2);
        verify(userRepo, times(1)).findById(2);
    }


    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteUserThrowsException() {
        // Arrange
        when(userRepo.findById(1)).thenReturn(Optional.empty());

        // Act
        userService.deleteUser(1);

        // Assert
        verify(userRepo, times(1)).findById(1);
    }

}