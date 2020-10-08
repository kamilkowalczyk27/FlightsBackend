package com.flights.domain;

import com.flights.exception.FlightNotFoundException;
import com.flights.repository.FlightRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class FlightEntityTestSuite {

    @Autowired
    FlightRepository flightRepository;

    @Test
    public void saveFlightTest() {
        //Given
        flightRepository.deleteAll();
        Aircraft aircraft = new Aircraft(2L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Moscow", "London", 2.2, aircraft);
        //When
        Flight savedFlight = flightRepository.save(flight);
        long flightId = savedFlight.getId();
        //Then
        Assert.assertEquals("Moscow",savedFlight.getDeparture());
        Assert.assertEquals("London", savedFlight.getArrival());
        Assert.assertEquals("Airbus a320", savedFlight.getAircraft().getModel());
    }

    @Test
    public void readFlightTest() throws FlightNotFoundException {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);
        //When
        Flight savedFlight = flightRepository.save(flight);
        long flightId = savedFlight.getId();
        Flight resultReadFlight = flightRepository.findById(flightId).orElseThrow(FlightNotFoundException::new);
        //Then
        Assert.assertEquals("Berlin", resultReadFlight.getDeparture());
        Assert.assertEquals("London", resultReadFlight.getArrival());
        Assert.assertEquals("Airbus a320", resultReadFlight.getAircraft().getModel());
    }

    @Test
    public void updateFlightTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);
        //When
        flightRepository.save(flight);
        flight.setDeparture("Moscow");
        flight.setArrival("Warsaw");
        flightRepository.save(flight);
        //Then
        Assert.assertEquals("Moscow", flight.getDeparture());
        Assert.assertEquals("Warsaw", flight.getArrival());
    }

    @Test
    public void deleteFlightTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);
        //When
        Flight savedFlight = flightRepository.save(flight);
        long flightId = savedFlight.getId();
        long countBeforeDelete = flightRepository.count();
        flightRepository.deleteById(flightId);
        long countAfterDelete = flightRepository.count();
        //Then
        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);
    }
}
