package com.flights.domain;

import com.flights.exception.AircraftNotFoundException;
import com.flights.repository.AircraftRepository;
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
public class AircraftEntityTestSuite {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Test
    public void saveAircraftTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When
        aircraftRepository.save(aircraft);
        long aircraftId = aircraft.getId();
        //Then
        Assert.assertEquals(1, aircraftId);
        Assert.assertEquals("Airbus a320", aircraft.getModel());
        Assert.assertEquals(11, aircraft.getHeight(),0.001);
        Assert.assertEquals(37, aircraft.getLength(),0.001);
    }

    @Test  //działa
    public void readAircraftTest() throws AircraftNotFoundException {
        //Given

        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When
        Aircraft savedAircraft = aircraftRepository.save(aircraft);
        System.out.println(aircraft.getId());
        long aircraftId = savedAircraft.getId();
        System.out.println(aircraft.getId());
        Aircraft resultReadAircraft = aircraftRepository.findById(aircraftId).orElseThrow(AircraftNotFoundException::new);
        //Then
        Assert.assertEquals("Airbus a320", resultReadAircraft.getModel());
        Assert.assertEquals(11, resultReadAircraft.getHeight(),0.001);
        Assert.assertEquals(37, resultReadAircraft.getLength(),0.001);
    }

    @Test
    public void updateAircraftTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When
        aircraftRepository.save(aircraft);
        aircraft.setModel("Boeing 737");
        aircraftRepository.save(aircraft);
        //Then
        Assert.assertEquals("Boeing 737", aircraft.getModel());
    }

    @Test
    public void deleteAircraftTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        //When // działa
        Aircraft saveAircraft = aircraftRepository.save(aircraft);
        long aircraftId = saveAircraft.getId();
        long countBeforeDelete = aircraftRepository.count();
        aircraftRepository.deleteById(aircraftId);
        long countAfterDelete = aircraftRepository.count();
        //Then
        Assert.assertEquals(countBeforeDelete -1, countAfterDelete);

        System.out.println(countBeforeDelete);
        System.out.println(countAfterDelete);
    }
}
