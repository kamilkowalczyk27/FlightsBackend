package com.flights.service;

import com.flights.domain.Aircraft;
import com.flights.domain.Flight;
import com.flights.repository.FlightRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Transactional
@RunWith(MockitoJUnitRunner.Silent.class)
public class FlightDbServiceTestSuite {

    @InjectMocks
    private FlightDbService flightDbService;

    @Mock
    private FlightRepository flightRepository;

    @Test
    public void getAllFlightsTest() {
        //Given
        Flight flight1 = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        Flight flight2 = new Flight(2L, "Madrid", "Moscow", 4.5, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        //When
        when(flightDbService.getAllFlights()).thenReturn(flights);
        //Then
        assertEquals(2, flights.size());
    }

    @Test
    public void getFlightTest() {
        //Given
        Flight flight = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        Long flightIdTest = flight.getId();
        String flightDepartureTest = flight.getDeparture();
        //When
        when(flightDbService.getFlight(flightIdTest)).thenReturn(java.util.Optional.of(flight));
        //Then
        assertEquals("Berlin", flightDepartureTest);
    }

    @Test
    public void saveFlightTest() {
        //Given
        Flight flight = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        //When
        when(flightRepository.save(flight)).thenReturn(flight);
        Flight savedFlight = flightDbService.saveFlight(flight);
        //Then
        assertEquals(flight.getId(), savedFlight.getId());
        assertEquals(flight.getDeparture(), savedFlight.getDeparture());
        assertEquals(flight.getAircraft(), savedFlight.getAircraft());
    }

    @Test
    public void deleteByIdFlightTest() {
        //Given
        Flight flight1 = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        Flight flight2 = new Flight(2L, "Madrid", "Moscow", 4.5, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        List<Flight> flights = new ArrayList<>();
        flights.add(flight1);
        flights.add(flight2);
        //When
        flightDbService.deleteById(2L);
        //Then
        verify(flightRepository, times(1)).deleteById(2L);
    }

    @Test
    public void deleteAllFlightsTest() {
        //Given
        Flight flight = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        //When
        flightDbService.deleteAllFlights();
        //Then
        verify(flightRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllFlightsTest() {
        //Given
        Flight flight = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        flightRepository.save(flight);
        long countAllFlights = flightRepository.count();
        //When
        when(flightDbService.countAllFlights()).thenReturn(countAllFlights);
        //Then
        verify(flightRepository, times(1)).count();
    }
}
