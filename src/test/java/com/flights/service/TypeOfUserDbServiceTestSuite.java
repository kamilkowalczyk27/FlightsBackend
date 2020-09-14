package com.flights.service;

import com.flights.domain.TypeOfUser;
import com.flights.repository.TypeOfUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TypeOfUserDbServiceTestSuite {

    @InjectMocks
    private TypeOfUserDbService typeOfUserDbService;

    @Mock
    private TypeOfUserRepository typeOfUserRepository;

    @Test
    public void getAllTypeOfUsersTest() {
        //Given
        TypeOfUser typeOfUser1 = new TypeOfUser(1L, "Pilot");
        TypeOfUser typeOfUser2 = new TypeOfUser(2L, "Passenger");
        List<TypeOfUser> typeOfUsers = new ArrayList<>();
        typeOfUsers.add(typeOfUser1);
        typeOfUsers.add(typeOfUser2);
        //When
        when(typeOfUserDbService.getAllTypeOfUsers()).thenReturn(typeOfUsers);
        //Then
        assertEquals(2, typeOfUsers.size());
    }

    @Test
    public void getTypeOfUserTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot");
        Long typeOfUserIdTest = typeOfUser.getId();
        String typeOfUserNameTest = typeOfUser.getName();
        //When
        when(typeOfUserDbService.getTypeOfUser(typeOfUserIdTest)).thenReturn(java.util.Optional.of(typeOfUser));
        //Then
        assertEquals("Pilot", typeOfUserNameTest);
    }

    @Test
    public void saveTypeOfUserTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot");
        //When
        when(typeOfUserRepository.save(typeOfUser)).thenReturn(typeOfUser);
        TypeOfUser savedTypeOfUser = typeOfUserDbService.saveTypeOfUser(typeOfUser);
        //Then
        assertEquals(typeOfUser.getId(), savedTypeOfUser.getId());
        assertEquals(typeOfUser.getName(), savedTypeOfUser.getName());
    }

    @Test
    public void deleteByIdTypeOfUserTest() {
        //Given
        TypeOfUser typeOfUser1 = new TypeOfUser(1L, "Pilot");
        TypeOfUser typeOfUser2 = new TypeOfUser(2L, "Passenger");
        List<TypeOfUser> typeOfUsers = new ArrayList<>();
        typeOfUsers.add(typeOfUser1);
        typeOfUsers.add(typeOfUser2);
        //When
        typeOfUserDbService.deleteById(2L);
        //Then
        verify(typeOfUserRepository, times(1)).deleteById(2L);
    }

    @Test
    public void deleteAllTypeOfUsersTest() {
        //Given
        TypeOfUser typeOfUser1 = new TypeOfUser(1L, "Pilot");
        TypeOfUser typeOfUser2 = new TypeOfUser(2L, "Passenger");
        List<TypeOfUser> typeOfUsers = new ArrayList<>();
        typeOfUsers.add(typeOfUser1);
        typeOfUsers.add(typeOfUser2);
        //When
        typeOfUserDbService.deleteAllTypeOfUsers();
        //Then
        verify(typeOfUserRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllTypeOfUsersTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot");
        typeOfUserRepository.save(typeOfUser);
        long countAllTypeOfUsers = typeOfUserRepository.count();
        //When
        when(typeOfUserDbService.countAllTypeOfUsers()).thenReturn(countAllTypeOfUsers);
        //Then
        verify(typeOfUserRepository, times(1)).count();
    }
}
