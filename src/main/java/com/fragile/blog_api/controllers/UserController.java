package com.fragile.blog_api.controllers;

import com.fragile.blog_api.payloads.ApiResponse;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
     private UserService userService;

    //POST CREATE USER
    @PostMapping("/addUser")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
       UserDto createdUserDto = userService.createUser(userDto);
       return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }

    //UPDATE USER
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUserDto(@RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
      UserDto updatedUser = userService.updateUser(userDto, uid);
      return ResponseEntity.ok(updatedUser);

    }

    //DELETE USER
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
    }
 // GET ALL USERS
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //GET SINGLE USER
    @GetMapping("/{userId}")
    public  ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid){
        return ResponseEntity.ok(userService.getUserById(uid));
    }

}
