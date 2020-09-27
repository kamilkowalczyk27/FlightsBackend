package com.flights.controller;

import com.flights.domain.TypeOfUser;
import com.flights.dto.TypeOfUserDto;
import com.flights.mapper.TypeOfUserMapper;
import com.flights.service.TypeOfUserDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import com.google.gson.Gson;
import static org.mockito.Mockito.when;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@WebMvcTest(TypeOfUserController.class)
public class TypeOfUserControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeOfUserDbService typeOfUserDbService;

    @MockBean
    private TypeOfUserMapper typeOfUserMapper;

    @Test
    public void getTypesOfUsers() throws Exception {
        //Given
        TypeOfUserDto typeOfUserDto = new TypeOfUserDto(1L, "Pilot", new ArrayList<>());
        List<TypeOfUserDto> typeOfUserDtoList = new ArrayList<>();
        typeOfUserDtoList.add(typeOfUserDto);
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        List<TypeOfUser> typeOfUserList = new ArrayList<>();
        typeOfUserList.add(typeOfUser);

        when(typeOfUserMapper.mapToTypeOfUserDtoList(typeOfUserList)).thenReturn(typeOfUserDtoList);
        when(typeOfUserDbService.getAllTypeOfUsers()).thenReturn(typeOfUserList);
        //When & Then
        mockMvc.perform(get("/v1/typeOfUsers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Pilot")));
    }

    @Test
    public void getTypeOfUserTest() throws Exception {
        //Given
        TypeOfUserDto typeOfUserDto = new TypeOfUserDto(1L, "Pilot", new ArrayList<>());
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());

        when(typeOfUserMapper.mapToTypeOfUserDto(typeOfUser)).thenReturn(typeOfUserDto);
        when(typeOfUserDbService.getTypeOfUser(1L)).thenReturn(Optional.of(typeOfUser));
        //When & Then
        mockMvc.perform(get("/v1/typeOfUsers/1").contentType(MediaType.APPLICATION_JSON).param("typeOfUserId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Pilot")));
    }

    @Test
    public void createTypeOfUserTest() throws Exception {
        //Given
        TypeOfUserDto typeOfUserDto = new TypeOfUserDto(1L, "Pilot", new ArrayList<>());
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());

        when(typeOfUserMapper.mapToTypeOfUser(typeOfUserDto)).thenReturn(typeOfUser);
        when(typeOfUserDbService.saveTypeOfUser(typeOfUser)).thenReturn(typeOfUser);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(typeOfUserDto);
        //When & Then
        mockMvc.perform(post("/v1/typeOfUsers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTypeOfUserTest() throws Exception {
        //Given
        TypeOfUserDto typeOfUserDto = new TypeOfUserDto(1L, "Pilot", new ArrayList<>());
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());

        when(typeOfUserMapper.mapToTypeOfUser(ArgumentMatchers.any(TypeOfUserDto.class))).thenReturn(typeOfUser);
        when(typeOfUserDbService.saveTypeOfUser(typeOfUser)).thenReturn(typeOfUser);
        when(typeOfUserMapper.mapToTypeOfUserDto(typeOfUser)).thenReturn(typeOfUserDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(typeOfUserDto);
        //When & Then
        mockMvc.perform(put("/v1/typeOfUsers").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Pilot")));
    }

    @Test
    public void deleteTypeOfUserTest() throws Exception {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        Long typeOfUserId = typeOfUser.getId();

        when(typeOfUserDbService.getTypeOfUser(typeOfUserId)).thenReturn(Optional.of(typeOfUser));
        //When & Then
        mockMvc.perform(delete("/v1/typeOfUsers/1").contentType(MediaType.APPLICATION_JSON).param("typeOfUserId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEmptyTypeOfUsersListTest() throws Exception {
        //Given
        List<TypeOfUser> typeOfUserList = new ArrayList<>();

        when(typeOfUserDbService.getAllTypeOfUsers()).thenReturn(typeOfUserList);
        //When & Then
        mockMvc.perform(get("/v1/typeOfUsers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}