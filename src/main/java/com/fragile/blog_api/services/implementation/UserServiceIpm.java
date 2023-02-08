package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.repositories.UserRepo;
import com.fragile.blog_api.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIpm implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.userDtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return this.userToUserDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = userRepo.save(user);
        return this.userToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        return userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        userRepo.delete(user);
    }

    public User userDtoToUser(UserDto userDto) {
        //        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToUserDto(User user) {
        //        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return modelMapper.map(user, UserDto.class);
    }
}
