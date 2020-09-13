package com.flights.domain;

import com.flights.repository.AircraftRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class AircraftEntityTestSuite {

    @Autowired
    AircraftRepository aircraftRepository;

    @Test
    public void testSaveAircraft() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828));
        //When
        aircraftRepository.save(aircraft);
        long aircraftId = aircraft.getId();
        //Then
        Assert.assertEquals(1, aircraftId);
        Assert.assertEquals("Airbus a320", aircraft.getModel());
        Assert.assertEquals(11, aircraft.getHeight(),0.001);
        Assert.assertEquals(37, aircraft.getLength(),0.001);
    }

    @Test
    public void testReadAircraft() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828));
        //When
        aircraftRepository.save(aircraft);
        long aircraftId = aircraft.getId();
        //Then
        Optional<Aircraft> readAircraft = aircraftRepository.findById(aircraftId);
        Assert.assertEquals("Airbus a320", aircraft.getModel());
        Assert.assertEquals(11, aircraft.getHeight(),0.001);
        Assert.assertEquals(37, aircraft.getLength(),0.001);
    }

    @Test
    public void testUpdateAircraft() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828));
        //When
        aircraftRepository.save(aircraft);
        long aircraftId = aircraft.getId();
        aircraft.setModel("Boeing 737");
        aircraftRepository.save(aircraft);
        //Then
        Assert.assertEquals("Boeing 737", aircraft.getModel());
    }

    @Test
    public void testDeleteAircraft() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828));
        //When
        Aircraft saveAircraft = aircraftRepository.save(aircraft);
        long aircraftId = saveAircraft.getId();
        long countBeforeDelete = aircraftRepository.count();
        aircraftRepository.deleteById(aircraftId);
        long countAfterDelete = aircraftRepository.count();

//        aircraftRepository.save(aircraft);
//        long aircraftId = aircraft.getId();
//        long countBeforeDelete = aircraftRepository.count();
//        aircraftRepository.deleteById(aircraftId);
//        long countAfterDelete = aircraftRepository.count();

        //Then
        Assert.assertEquals(1, countBeforeDelete);
        Assert.assertEquals(0, countAfterDelete);

        System.out.println(countBeforeDelete);
        System.out.println(countAfterDelete);

        //System.out.println(aircraftRepository.count()); // czemu nalicza mi id za każdym razem włączenia testu? deleteAll działa


    }
}

