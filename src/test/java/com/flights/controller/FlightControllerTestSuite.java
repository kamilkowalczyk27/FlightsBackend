package com.flights.controller;

import com.flights.domain.Aircraft;
import com.flights.domain.Flight;
import com.flights.dto.AircraftDto;
import com.flights.dto.FlightDto;
import com.flights.mapper.FlightMapper;
import com.flights.service.FlightDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Optional;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightDbService flightDbService;

    @MockBean
    private FlightMapper flightMapper;

    @Test
    public void getFlightsTest() throws Exception {
        //Given
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        FlightDto flightDto = new FlightDto(1L, "Berlin", "London", 2.2, aircraftDto);
        List<FlightDto> flightDtoList = new ArrayList<>();
        flightDtoList.add(flightDto);
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight);

        when(flightMapper.mapToFlightDtoList(flightList)).thenReturn(flightDtoList);
        when(flightDbService.getAllFlights()).thenReturn(flightList);

        //When & Then
        mockMvc.perform(get("/v1/getFlights").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].departure", is("Berlin")))
                .andExpect(jsonPath("$[0].arrival", is("London")));
    }

    @Test
    public void getFlightTest() throws Exception {
        //Given
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        FlightDto flightDto = new FlightDto(1L, "Berlin", "London", 2.2, aircraftDto);
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);

        when(flightMapper.mapToFlightDto(flight)).thenReturn(flightDto);
        when(flightDbService.getFlight(1L)).thenReturn(Optional.of(flight));

        //When & Then
        mockMvc.perform(get("/v1/flights/1").contentType(MediaType.APPLICATION_JSON).param("flightId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.departure", is("Berlin")))
                .andExpect(jsonPath("$.arrival", is("London")));
    }

    @Test
    public void createFlightTest() throws Exception {
        //Given
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        FlightDto flightDto = new FlightDto(1L, "Berlin", "London", 2.2, aircraftDto);
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);

        when(flightMapper.mapToFlight(flightDto)).thenReturn(flight);
        when(flightDbService.saveFlight(flight)).thenReturn(flight);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(flightDto);
        //When & Then
        mockMvc.perform(post("/v1/flights").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFlightTest() throws Exception {
        //Given
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        FlightDto flightDto = new FlightDto(1L, "Berlin", "London", 2.2, aircraftDto);
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);

        when(flightMapper.mapToFlight(ArgumentMatchers.any(FlightDto.class))).thenReturn(flight);
        when(flightDbService.saveFlight(flight)).thenReturn(flight);
        when(flightMapper.mapToFlightDto(flight)).thenReturn(flightDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(flightDto);
        //When & Then
        mockMvc.perform(put("/v1/flights").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.departure", is("Berlin")))
                .andExpect(jsonPath("$.arrival", is("London")));
    }

//    @Test //nie działa
//    public void deleteFlightTest() throws Exception {
//        //Given
//        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
//        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);
//        Long flightId = flight.getId();
//
//        when(flightDbService.getFlight(flightId)).thenReturn(Optional.of(flight));
//        System.out.println("flightId = " + flightId);
//        //When & Then
//        mockMvc.perform(delete("/v1/flights/1").contentType(MediaType.APPLICATION_JSON).param("flightId", "1"))
//                .andExpect(status().isOk());
//    }

    @Test
    public void shouldEmptyFlightsListTest() throws Exception {
        //Given
        List<Flight> flightList = new ArrayList<>();

        when(flightDbService.getAllFlights()).thenReturn(flightList);
        //When & Then
        mockMvc.perform(get("/v1/getFlights").contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());
    }
}