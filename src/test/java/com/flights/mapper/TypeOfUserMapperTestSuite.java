package com.flights.mapper;

import com.flights.domain.TypeOfUser;
import com.flights.dto.TypeOfUserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeOfUserMapperTestSuite {

    @Autowired
    private TypeOfUserMapper typeOfUserMapper;

    @Test
    public void mapToTypeOfUserTest() {
        //Given
        TypeOfUserDto typeOfUserDto = new TypeOfUserDto(1L, "Pilot", new ArrayList<>());
        //When
        TypeOfUser typeOfUser = typeOfUserMapper.mapToTypeOfUser(typeOfUserDto);
        //Then
        assertEquals(1, (long)typeOfUser.getId());
        assertEquals("Pilot", typeOfUser.getName());
    }

    @Test
    public void mapToTypeOfUserDtoTest() {
        //Given
        TypeOfUser typeOfUser = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        //When
        TypeOfUserDto typeOfUserDto = typeOfUserMapper.mapToTypeOfUserDto(typeOfUser);
        //Then
        assertEquals(1, (long)typeOfUserDto.getId());
        assertEquals("Pilot", typeOfUserDto.getName());
    }

    @Test
    public void mapToTypeOfUserListTest() {
        //Given
        TypeOfUserDto typeOfUserDto1 = new TypeOfUserDto(1L, "Pilot", new ArrayList<>());
        TypeOfUserDto typeOfUserDto2 = new TypeOfUserDto(2L, "Passenger", new ArrayList<>());
        List<TypeOfUserDto> typeOfUserDtoList = new ArrayList<>();
        typeOfUserDtoList.add(typeOfUserDto1);
        typeOfUserDtoList.add(typeOfUserDto2);
        //When
        List<TypeOfUser> typeOfUserList = typeOfUserMapper.mapToTypeOfUserList(typeOfUserDtoList);
        //Then
        assertEquals(2, typeOfUserList.size());
        assertEquals("Pilot", typeOfUserList.get(0).getName());
        assertEquals("Passenger", typeOfUserList.get(1).getName());
    }

    @Test
    public void mapToTypeOfUserDtoListTest() {
        //Given
        TypeOfUser typeOfUser1 = new TypeOfUser(1L, "Pilot", new ArrayList<>());
        TypeOfUser typeOfUser2 = new TypeOfUser(2L, "Passenger", new ArrayList<>());
        List<TypeOfUser> typeOfUsers = new ArrayList<>();
        typeOfUsers.add(typeOfUser1);
        typeOfUsers.add(typeOfUser2);
        //When
        List<TypeOfUserDto> typeOfUserDtoList = typeOfUserMapper.mapToTypeOfUserDtoList(typeOfUsers);
        //Then
        assertEquals(2, typeOfUserDtoList.size());
        assertEquals("Pilot", typeOfUserDtoList.get(0).getName());
        assertEquals("Passenger", typeOfUserDtoList.get(1).getName());
    }
}
