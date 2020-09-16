package com.flights.mapper;

import com.flights.domain.Aircraft;
import com.flights.domain.Flight;
import com.flights.dto.AircraftDto;
import com.flights.dto.FlightDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightMapperTestSuite {

    @Autowired
    private FlightMapper flightMapper;

    @Test
    public void mapToFlightTest() {
        //Given
        FlightDto flightDto = new FlightDto(1L, "Berlin", "London", 2.2, new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        //When
        Flight flight = flightMapper.mapToFlight(flightDto);
        //Then
        assertEquals(1, (long)flight.getId());
        assertEquals("Berlin", flight.getDeparture());
        assertEquals("London", flight.getArrival());
    }

    @Test
    public void mapToFlightDtoTest() {
        //Given
        Flight flight = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        //When
        FlightDto flightDto = flightMapper.mapToFlightDto(flight);
        //Then
        assertEquals(1, (long)flightDto.getId());
        assertEquals("Berlin", flightDto.getDeparture());
        assertEquals("London", flightDto.getArrival());
    }

    @Test
    public void mapToFlightListTest() {
        //Given
        FlightDto flightDto1 = new FlightDto(1L, "Berlin", "London", 2.2, new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        FlightDto flightDto2 = new FlightDto(2L, "Madrid", "Moscow", 4.5, new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        List<FlightDto> flightDtoList = new ArrayList<>();
        flightDtoList.add(flightDto1);
        flightDtoList.add(flightDto2);
        //When
        List<Flight> flightList = flightMapper.mapToFlightList(flightDtoList);
        //Then
        assertEquals(2, flightList.size());
        assertEquals("Berlin", flightList.get(0).getDeparture());
        assertEquals("London", flightList.get(0).getArrival());
    }

    @Test
    public void mapToFlightDtoListTest() {
        //Given
        Flight flight1 = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        Flight flight2 = new Flight(2L, "Madrid", "Moscow", 4.5, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        List<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight2);
        //When
        List<FlightDto> flightDtoList = flightMapper.mapToFlightDtoList(flightList);
        //Then
        assertEquals(2, flightDtoList.size());
        assertEquals("Berlin", flightDtoList.get(0).getDeparture());
        assertEquals("London", flightDtoList.get(0).getArrival());
    }
}
