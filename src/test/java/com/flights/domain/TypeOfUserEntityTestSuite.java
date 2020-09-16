package com.flights.domain;

import com.flights.exception.TypeOfUserNotFoundException;
import com.flights.repository.TypeOfUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeOfUserEntityTestSuite {

    @Autowired
    TypeOfUserRepository typeOfUserRepository;

    @Test
    public void saveTypeOfUserTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        //When
        typeOfUserRepository.save(typeOfUser);
        long typeOfUserId = typeOfUser.getId();
        //Then
        Assert.assertEquals(1, typeOfUserId);
        Assert.assertEquals("Pilot", typeOfUser.getName());
    }

    @Test
    public void readTypeOfUserTest() throws TypeOfUserNotFoundException {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        //When
        TypeOfUser savedTypeOfUser = typeOfUserRepository.save(typeOfUser);
        long typeOfUserId = savedTypeOfUser.getId();
        TypeOfUser resultReadTypeOfUser = typeOfUserRepository.findById(typeOfUserId).orElseThrow(TypeOfUserNotFoundException::new);
        //Then
        Assert.assertEquals("Pilot", resultReadTypeOfUser.getName());
    }

    @Test
    public void updateTypeOfUserTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        //When
        typeOfUserRepository.save(typeOfUser);
        typeOfUser.setName("Passenger");
        typeOfUserRepository.save(typeOfUser);
        //Then
        Assert.assertEquals("Passenger", typeOfUser.getName());
    }

    @Test
    public void deleteTypeOfUserTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        //When
        TypeOfUser savedTypeOfUser = typeOfUserRepository.save(typeOfUser);
        long typeOfUserId = savedTypeOfUser.getId();
        long countBeforeDelete = typeOfUserRepository.count();
        typeOfUserRepository.deleteById(typeOfUserId);
        long countAfterDelete = typeOfUserRepository.count();
        //Then
        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);
    }
}
