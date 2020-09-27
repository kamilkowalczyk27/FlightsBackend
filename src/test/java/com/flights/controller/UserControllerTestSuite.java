package com.flights.controller;

import com.flights.domain.User;
import com.flights.dto.UserDto;
import com.flights.mapper.UserMapper;
import com.flights.service.UserDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.mockito.ArgumentMatchers;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserMapper userMapper;

    @MockBean
    private UserDbService userDbService;

    @Test
    public void getUsersTest() throws Exception {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<User> userList = new ArrayList<>();
        userList.add(user);
        UserDto userDto = new UserDto(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);

        when(userMapper.mapToUserDtoList(userList)).thenReturn(userDtoList);
        when(userDbService.getAllUsers()).thenReturn(userList);
        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("John")))
                .andExpect(jsonPath("$[0].lastName", is("Smith")))
                .andExpect(jsonPath("$[0].email", is("johnsmith@gmail.com")));
    }

    @Test
    public void getUserTest() throws Exception {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        UserDto userDto = new UserDto(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());

        when(userMapper.mapToUserDto(user)).thenReturn(userDto);
        when(userDbService.getUser(1L)).thenReturn(Optional.of(user));
        //When & Then
        mockMvc.perform(get("/v1/users/1").contentType(MediaType.APPLICATION_JSON).param("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Smith")))
                .andExpect(jsonPath("$.email", is("johnsmith@gmail.com")));
    }

    @Test
    public void createUserTest() throws Exception {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        UserDto userDto = new UserDto(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());

        when(userMapper.mapToUser(userDto)).thenReturn(user);
        when(userDbService.saveUser(user)).thenReturn(user);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc.perform(post("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserTest() throws Exception {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        UserDto userDto = new UserDto(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());

        when(userMapper.mapToUser(ArgumentMatchers.any(UserDto.class))).thenReturn(user);
        when(userDbService.saveUser(user)).thenReturn(user);
        when(userMapper.mapToUserDto(user)).thenReturn(userDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(userDto);
        //When & Then
        mockMvc.perform(put("/v1/users").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Smith")))
                .andExpect(jsonPath("$.email", is("johnsmith@gmail.com")));
    }

    @Test
    public void deleteUserTest() throws Exception {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        Long userId = user.getId();

        when(userDbService.getUser(userId)).thenReturn(Optional.of(user));
        //When & Then
        mockMvc.perform(delete("/v1/users/1").contentType(MediaType.APPLICATION_JSON).param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchEmptyUsersListTest() throws Exception {
        //Given
        List<User> userList = new ArrayList<>();

        when(userDbService.getAllUsers()).thenReturn(userList);
        //When & Then
        mockMvc.perform(get("/v1/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}