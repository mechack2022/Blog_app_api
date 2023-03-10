package com.fragile.blog_api.controllers;

import com.fragile.blog_api.entities.User;
import com.fragile.blog_api.payloads.ApiResponse;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.payloads.UserResponseDto;
import com.fragile.blog_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    // PLEASE CHANGE YOUR DATABASE CONFIGURATION BACK TO jdbc:mysql://localhost:3306/blog_api_db
    @Autowired
     private UserService userService;

    //POST CREATE USER
    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserDto userDto){
       UserResponseDto createdUserDto = userService.createUser(userDto);
       return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
    }


    //UPDATE USER
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUserDto(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid) {
        UserResponseDto updatedUser = userService.updateUser(userDto, uid);
      return ResponseEntity.ok(updatedUser);

    }
    //DELETE USER
    @DeleteMapping("/{userId}")
    @Operation(summary = "delete user",
            security = {@SecurityRequirement(name = "bearer-token")},
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ApiResponse.class)))})
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully", true), HttpStatus.OK);
    }
 // GET ALL USERS
    @GetMapping("/")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //GET SINGLE USER
    @GetMapping("/{userId}")
    public  ResponseEntity<UserResponseDto> getSingleUser(@PathVariable("userId") Integer uid){
        return ResponseEntity.ok(userService.getUserById(uid));
    }

}
