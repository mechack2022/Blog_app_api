package com.fragile.blog_api.controllers;

import com.fragile.blog_api.event.UserCreationEvent;
import com.fragile.blog_api.exceptions.ApiException;
import com.fragile.blog_api.payloads.JwtAuthRequest;
import com.fragile.blog_api.payloads.JwtAuthResponse;
import com.fragile.blog_api.payloads.UserDto;
import com.fragile.blog_api.security.JwtTokenHelper;
import com.fragile.blog_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final JwtTokenHelper jwtTokenHelper;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    private final ApplicationEventPublisher applicationEventPublisher;

    @PostMapping("/login")
    @Operation(
            description = "login service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successfully sign in",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"code\": 400, \"status\": \"OK\", \"message\": \"User successfully register\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "bad request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{\"code\": 400, \"status\": \"Bad request\", \"message\": \"Bad request\"}")
                            )
                    )

            })
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        String generateToken = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(generateToken);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


    public void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (BadCredentialsException e) {
            System.out.println("invalid credentials");
            throw new ApiException("Invalid username or password !!");
        }

    }

    @PostMapping("/register")
    @Operation(summary = "Register New User", responses = {
            @ApiResponse(responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)))})
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.registerNewUser(userDto);
        applicationEventPublisher.publishEvent(new UserCreationEvent(userDto, "user successfully registered"));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
