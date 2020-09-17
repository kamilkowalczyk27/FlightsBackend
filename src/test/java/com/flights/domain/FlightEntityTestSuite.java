package com.flights.domain;

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
        Flight flight = new Flight(1L, "Berlin", "London", 2.2, new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>()));
        System.out.println(flight.getId());
        //When
        Flight savedFlight = flightRepository.save(flight);
        long flightId = savedFlight.getId();
        //Then
        Assert.assertEquals(1, flightId);

    }
}
