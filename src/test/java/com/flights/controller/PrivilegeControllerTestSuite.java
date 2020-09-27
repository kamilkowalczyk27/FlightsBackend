package com.flights.controller;

import com.flights.domain.Privilege;
import com.flights.dto.PrivilegeDto;
import com.flights.mapper.PrivilegeMapper;
import com.flights.service.PrivilegeDbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PrivilegeController.class)
public class PrivilegeControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrivilegeDbService privilegeDbService;

    @MockBean
    private PrivilegeMapper privilegeMapper;

    @Test
    public void getPrivileges() throws Exception {
        //Given
        PrivilegeDto privilegeDto = new PrivilegeDto(1L,"privilegeTest", new ArrayList<>());
        List<PrivilegeDto> privilegeDtoList = new ArrayList<>();
        privilegeDtoList.add(privilegeDto);
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        List<Privilege> privilegeList = new ArrayList<>();
        privilegeList.add(privilege);

        when(privilegeMapper.mapToPrivilegeDtoList(privilegeList)).thenReturn(privilegeDtoList);
        when(privilegeDbService.getAllPrivileges()).thenReturn(privilegeList);
        //When & Then
        mockMvc.perform(get("/v1/privileges").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("privilegeTest")));
    }

    @Test
    public void getPrivilegeTest() throws Exception {
        //Given
        PrivilegeDto privilegeDto = new PrivilegeDto(1L,"privilegeTest", new ArrayList<>());
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());

        when(privilegeMapper.mapToPrivilegeDto(privilege)).thenReturn(privilegeDto);
        when(privilegeDbService.getPrivilege(1L)).thenReturn(Optional.of(privilege));
        //When & Then
        mockMvc.perform(get("/v1/privileges/1").contentType(MediaType.APPLICATION_JSON).param("privilegeId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("privilegeTest")));
    }

    @Test
    public void createPrivilegeTest() throws Exception {
        //Given
        PrivilegeDto privilegeDto = new PrivilegeDto(1L,"privilegeTest", new ArrayList<>());
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());

        when(privilegeMapper.mapToPrivilege(privilegeDto)).thenReturn(privilege);
        when(privilegeDbService.savePrivilege(privilege)).thenReturn(privilege);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(privilegeDto);
        //When & Then
        mockMvc.perform(post("/v1/privileges").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePrivilegeTest() throws Exception {
        //Given
        PrivilegeDto privilegeDto = new PrivilegeDto(1L,"privilegeTest", new ArrayList<>());
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());

        when(privilegeMapper.mapToPrivilege(ArgumentMatchers.any(PrivilegeDto.class))).thenReturn(privilege);
        when(privilegeDbService.savePrivilege(privilege)).thenReturn(privilege);
        when(privilegeMapper.mapToPrivilegeDto(privilege)).thenReturn(privilegeDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(privilegeDto);
        //When & Then
        mockMvc.perform(put("/v1/privileges").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("privilegeTest")));
    }

    @Test
    public void deletePrivilegeTest() throws Exception {
        //Given
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        Long privilegeId = privilege.getId();

        when(privilegeDbService.getPrivilege(privilegeId)).thenReturn(Optional.of(privilege));
        //When & Then
        mockMvc.perform(delete("/v1/privileges/1").contentType(MediaType.APPLICATION_JSON).param("privilegeId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldEmptyPrivilegesListTest() throws Exception {
        //Given
        List<Privilege> privilegeList = new ArrayList<>();

        when(privilegeDbService.getAllPrivileges()).thenReturn(privilegeList);
        //When & Then
        mockMvc.perform(get("/v1/privileges").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}
