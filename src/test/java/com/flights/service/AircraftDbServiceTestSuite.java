package com.flights.service;

import com.flights.domain.Aircraft;
import com.flights.repository.AircraftRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AircraftDbServiceTestSuite {

    @InjectMocks
    private AircraftDbService aircraftDbService;

    @Mock
    private AircraftRepository aircraftRepository;

    @Test
    public void getAllAircraftsTest() {
        //Given
        Aircraft aircraft1 = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Aircraft aircraft2 = new Aircraft(2L,"Airbus a380", 10, 39 , new BigDecimal(850), new BigDecimal(31000), new BigDecimal(6000), new BigDecimal(5500), new BigDecimal(850), new ArrayList<>());
        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(aircraft1);
        aircrafts.add(aircraft2);
        //When
        when(aircraftDbService.getAllAircrafts()).thenReturn(aircrafts);
        //Then
        assertEquals(2, aircrafts.size());
    }

    @Test
    public void getAircraftTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Long aircraftIdTest = aircraft.getId();
        String aircraftModelTest = aircraft.getModel();
        //When
        when(aircraftDbService.getAircraft(aircraftIdTest)).thenReturn(java.util.Optional.of(aircraft));
        //Then
        assertEquals("Airbus a320", aircraftModelTest );
    }

    @Test
    public void saveAircraftTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When
        when(aircraftRepository.save(aircraft)).thenReturn(aircraft);
        Aircraft savedAircraft = aircraftDbService.saveAircraft(aircraft);
        //Then
        assertEquals(aircraft.getId(), savedAircraft.getId());
        assertEquals(aircraft.getModel(), savedAircraft.getModel());
        assertEquals(aircraft.getHeight(), savedAircraft.getHeight(), 0.001);
    }

    @Test
    public void deleteByIdAircraftTest() {
        //Given
        Aircraft aircraft1 = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Aircraft aircraft2 = new Aircraft(2L,"Airbus a380", 10, 39 , new BigDecimal(850), new BigDecimal(31000), new BigDecimal(6000), new BigDecimal(5500), new BigDecimal(850), new ArrayList<>());
        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(aircraft1);
        aircrafts.add(aircraft2);
        //When
        aircraftDbService.deleteById(2L);
        //Then
        verify(aircraftRepository, times(1)).deleteById(2L);
    }

    @Test
    public void deleteAllAircraftsTest() {
        //Given
        Aircraft aircraft1 = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Aircraft aircraft2 = new Aircraft(2L,"Airbus a380", 10, 39 , new BigDecimal(850), new BigDecimal(31000), new BigDecimal(6000), new BigDecimal(5500), new BigDecimal(850), new ArrayList<>());
        List<Aircraft> aircrafts = new ArrayList<>();
        aircrafts.add(aircraft1);
        aircrafts.add(aircraft2);
        //When
        aircraftDbService.deleteAllAircrafts();
        //Then
        verify(aircraftRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllAircraftsTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        aircraftRepository.save(aircraft);
        long countAllAircrafts = aircraftRepository.count();
        //When
        when(aircraftDbService.countAllAircraft()).thenReturn(countAllAircrafts);
        //Then
        verify(aircraftRepository, times(1)).count();
    }
}
