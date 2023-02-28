package com.fragile.blog_api.controllers;


import com.fragile.blog_api.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Test User");
        userDto.setEmail("test@example.com");
        userDto.setPassword("password123");
        userDto.setAbout("Test user description");
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(1);
        responseDto.setName("Test User");
        responseDto.setEmail("test@example.com");
        responseDto.setAbout("Test user description");

        given(userService.createUser(userDto)).willReturn(responseDto);

        mockMvc.perform(post("/api/users/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Test User")))
                .andExpect(jsonPath("$.email", is("test@example.com")))
                .andExpect(jsonPath("$.about", is("Test user description")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setName("Updated Test User");
        userDto.setEmail("updated_test@example.com");
        userDto.setPassword("password123");
        userDto.setAbout("Updated test user description");
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(1);
        responseDto.setName("Updated Test User");
        responseDto.setEmail("updated_test@example.com");
        responseDto.setAbout("Updated test user description");

        given(userService.updateUser(userDto, 1)).willReturn(responseDto);

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Test User")))
                .andExpect(jsonPath("$.email", is("updated_test@example.com")))
                .andExpect(jsonPath("$.about", is("Updated test user description")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        ApiResponse response = new ApiResponse("user deleted successfully", true);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("user deleted successfully")))
                .andExpect(jsonPath("$.success", is(true)));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<UserResponseDto> responseDtos = new ArrayList<>();
        UserResponseDto user1 = new UserResponseDto();
        user1.setId(1);
        user1.setName("Test User 1");
        user1.setEmail("test1@example.com");
        user1.setAbout("Test user 1 description");
        UserResponseDto user2 = new UserResponseDto();
        user2.setId(2);
        user2.setName("Test User 2");
        user2.setEmail("test2@example.com");
        user2.setAbout("Test user 2 description");
        responseDtos.add(user1);
        responseDtos.add(user2);

        given(userService.getAllUsers()).willReturn(responseDtos);

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Test User 1")))
                .andExpect(jsonPath("$[0].email", is("test1@example.com")))
                .andExpect(jsonPath


                        // Test getSingleUser method
                        mockMvc.perform(get("/api/users/1"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(1)))
                                .andExpect(jsonPath("$.name", is("Test User 1")))
                                .andExpect(jsonPath("$.email", is("test1@example.com")))
                                .andExpect(jsonPath("$.about", is("This is the first test user")));

        // Test createUser method
        UserDto newUser = new UserDto();
        newUser.setName("Test User 3");
        newUser.setEmail("test3@example.com");
        newUser.setPassword("test123");
        newUser.setAbout("This is the third test user");

        mockMvc.perform(post("/api/users/addUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Test User 3")))
                .andExpect(jsonPath("$.email", is("test3@example.com")))
                .andExpect(jsonPath("$.about", is("This is the third test user")));

        // Test updateUser method
        UserDto updatedUser = new UserDto();
        updatedUser.setName("Updated Test User");
        updatedUser.setEmail("updatedtest1@example.com");
        updatedUser.setPassword("updatedtest123");
        updatedUser.setAbout("This is the updated test user");

        mockMvc.perform(put("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Test User")))
                .andExpect(jsonPath("$.email", is("updatedtest1@example.com")))
                .andExpect(jsonPath("$.about", is("This is the updated test user")));

        // Test deleteUser method
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success", is(true)))
                .andExpect(jsonPath("$.message", is("user deleted successfully")));
    }
}