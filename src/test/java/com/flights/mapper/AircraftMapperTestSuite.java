package com.flights.mapper;

import com.flights.domain.Aircraft;
import com.flights.dto.AircraftDto;
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
public class AircraftMapperTestSuite {

    @Autowired
    private AircraftMapper aircraftMapper;

    @Test
    public void mapToAircraftTest() {
        //Given
        AircraftDto aircraftDto = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When
        Aircraft aircraft = aircraftMapper.mapToAircraft(aircraftDto);
        //Then
        assertEquals(1, (long)aircraft.getId());
        assertEquals("Airbus a320", aircraft.getModel());
        assertEquals(11, aircraft.getHeight(), 0.001);
        assertEquals(37, aircraft.getLength(), 0.001);
    }

    @Test
    public void mapToAircraftDto() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When
        AircraftDto aircraftDto = aircraftMapper.mapToAircraftDto(aircraft);
        //Then
        assertEquals(1, (long)aircraftDto.getId());
        assertEquals("Airbus a320", aircraftDto.getModel());
        assertEquals(11, aircraftDto.getHeight(), 0.001);
        assertEquals(37, aircraftDto.getLength(), 0.001);
    }

    @Test
    public void mapToAircraftList() {
        //Given
        AircraftDto aircraftDto1 = new AircraftDto(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        AircraftDto aircraftDto2 = new AircraftDto(2L,"Airbus a380", 10, 39 , new BigDecimal(850), new BigDecimal(31000), new BigDecimal(6000), new BigDecimal(5500), new BigDecimal(850), new ArrayList<>());
        List<AircraftDto> aircraftDtoList = new ArrayList<>();
        aircraftDtoList.add(aircraftDto1);
        aircraftDtoList.add(aircraftDto2);
        //When
        List<Aircraft> aircraftList = aircraftMapper.mapToAircraftList(aircraftDtoList);
        //Then
        assertEquals(2, aircraftList.size());
        assertEquals("Airbus a320", aircraftList.get(0).getModel());
        assertEquals("Airbus a380", aircraftList.get(1).getModel());
        assertEquals(aircraftDtoList.get(0).getId(), aircraftList.get(0).getId());
    }

    @Test
    public void mapToAircraftDtoList() {
        //Given
        Aircraft aircraft1 = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Aircraft aircraft2 = new Aircraft(2L,"Airbus a380", 10, 39 , new BigDecimal(850), new BigDecimal(31000), new BigDecimal(6000), new BigDecimal(5500), new BigDecimal(850), new ArrayList<>());
        List<Aircraft> aircraftList = new ArrayList<>();
        aircraftList.add(aircraft1);
        aircraftList.add(aircraft2);
        //When
        List<AircraftDto> aircraftDtoList = aircraftMapper.mapToAircraftDtoList(aircraftList);
        //Then
        assertEquals(2, aircraftDtoList.size());
        assertEquals("Airbus a320", aircraftDtoList.get(0).getModel());
        assertEquals("Airbus a380", aircraftDtoList.get(1).getModel());
        assertEquals(aircraftList.get(0).getId(), aircraftDtoList.get(0).getId());
    }
}
