package com.flights.controller;

import com.flights.domain.Aircraft;
import com.flights.dto.AircraftDto;
import com.flights.facade.AircraftFacade;
import com.flights.mapper.AircraftMapper;
import com.flights.service.AircraftDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(AircraftController.class)
public class AircraftControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftDbService aircraftDbService;

    @MockBean
    private AircraftMapper aircraftMapper;

    @MockBean
    private AircraftFacade aircraftFacade;

    @Test
    public void getAircraftsTest() throws Exception {
        //Given
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        List<AircraftDto> aircraftDtoList = new ArrayList<>();
        aircraftDtoList.add(aircraftDto);
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft);

        when(aircraftMapper.mapToAircraftDtoList(aircraftList)).thenReturn(aircraftDtoList);
        when(aircraftDbService.getAllAircrafts()).thenReturn(aircraftList);

        //When & Then
        mockMvc.perform(get("/v1/aircrafts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].model", is("Airbus a320")))
                .andExpect(jsonPath("$[0].height", is(11.0)));
    }

    @Test
    public void getAircraftTest() throws Exception {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());

        when(aircraftMapper.mapToAircraftDto(aircraft)).thenReturn(aircraftDto);
        when(aircraftDbService.getAircraft(1L)).thenReturn(Optional.of(aircraft));

        //When & Then
        mockMvc.perform(get("/v1/aircrafts/1").contentType(MediaType.APPLICATION_JSON).param("aircraftId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.model", is("Airbus a320")))
                .andExpect(jsonPath("$.height", is(11.0)));
    }

    @Test
    public void deleteAircraftTest() throws Exception {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Long aircraftId = aircraft.getId();

        when(aircraftDbService.getAircraft(aircraftId)).thenReturn(Optional.of(aircraft));
        //When & Then
        mockMvc.perform(delete("/v1/aircrafts/1").contentType(MediaType.APPLICATION_JSON).param("aircraftId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void createAircraftTest() throws Exception {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());

        when(aircraftMapper.mapToAircraft(aircraftDto)).thenReturn(aircraft);
        when(aircraftDbService.saveAircraft(aircraft)).thenReturn(aircraft);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(aircraftDto);
        //When & Then
        mockMvc.perform(post("/v1/aircrafts").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateAircraftTest() throws Exception {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());

        when(aircraftMapper.mapToAircraft(ArgumentMatchers.any(AircraftDto.class))).thenReturn(aircraft);
        when(aircraftDbService.saveAircraft(aircraft)).thenReturn(aircraft);
        when(aircraftMapper.mapToAircraftDto(aircraft)).thenReturn(aircraftDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(aircraftDto);
        //When & Then
        mockMvc.perform(put("/v1/aircrafts").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.model", is("Airbus a320")));
    }

    @Test
    public void shouldEmptyAircraftsListTest() throws Exception {
        //Given
        List<Aircraft> aircraftList = new ArrayList<>();

        when(aircraftDbService.getAllAircrafts()).thenReturn(aircraftList);
        //When & Then
        mockMvc.perform(get("/v1/aircrafts").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}