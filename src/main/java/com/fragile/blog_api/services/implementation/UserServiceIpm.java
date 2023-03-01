package com.fragile.blog_api.services.implementation;

import com.fragile.blog_api.config.AppConstant;
import com.fragile.blog_api.entities.Role;
import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.exceptions.ResourceNotFoundException;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.repositories.RoleRepo;
import com.fragile.blog_api.payloads.UserResponseDto;
import com.fragile.blog_api.repositories.UserRepo;
import com.fragile.blog_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceIpm implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepo roleRepo;

    @Override
    public UserDto registerNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //ROLE
        Role role = roleRepo.findById(AppConstant.NORMAL_USER).get();
        user.getRoles().add(role);
        User registerUser = userRepo.save(user);
        return modelMapper.map(registerUser, UserDto.class);
    }

    @Override
    public UserResponseDto createUser(UserDto userDto) {
        User user = this.userDtoToUser(userDto);
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto updateUser(UserDto userDto, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id ", userId));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User savedUser = userRepo.save(user);
        return modelMapper.map(savedUser, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
         return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " Id ", id));
        userRepo.delete(user);
    }

    public User userDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
