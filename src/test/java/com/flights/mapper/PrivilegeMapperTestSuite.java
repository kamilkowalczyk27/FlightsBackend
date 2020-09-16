package com.flights.mapper;

import com.flights.domain.Privilege;
import com.flights.dto.PrivilegeDto;
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
public class PrivilegeMapperTestSuite {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Test
    public void mapToPrivilegeTest() {
        //Given
        PrivilegeDto privilegeDto = new PrivilegeDto(1L,"privilegeTest", new ArrayList<>());
        //When
        Privilege privilege = privilegeMapper.mapToPrivilege(privilegeDto);
        //Then
        assertEquals(1, (long)privilege.getId());
        assertEquals("privilegeTest", privilege.getName());
    }

    @Test
    public void mapToPrivilegeDtoTest() {
        //Given
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        //When
        PrivilegeDto privilegeDto = privilegeMapper.mapToPrivilegeDto(privilege);
        //Then
        assertEquals(1, (long)privilegeDto.getId());
        assertEquals("privilegeTest", privilegeDto.getName());
    }

    @Test
    public void mapToPrivilegeListTest() {
        //Given
        PrivilegeDto privilegeDto1 = new PrivilegeDto(1L,"privilegeTest1", new ArrayList<>());
        PrivilegeDto privilegeDto2 = new PrivilegeDto(2L,"privilegeTest2", new ArrayList<>());
        List<PrivilegeDto> privilegeDtoList = new ArrayList<>();
        privilegeDtoList.add(privilegeDto1);
        privilegeDtoList.add(privilegeDto2);
        //When
        List<Privilege> privilegeList = privilegeMapper.mapToPrivilegeList(privilegeDtoList);
        //Then
        assertEquals(2, privilegeList.size());
        assertEquals("privilegeTest1", privilegeList.get(0).getName());
        assertEquals("privilegeTest2", privilegeList.get(1).getName());
    }

    @Test
    public void mapToPrivilegeDtoListTest() {
        //Given
        Privilege privilege1 = new Privilege(1L,"privilegeTest1", new ArrayList<>());
        Privilege privilege2 = new Privilege(2L,"privilegeTest2", new ArrayList<>());
        List<Privilege> privilegeList = new ArrayList<>();
        privilegeList.add(privilege1);
        privilegeList.add(privilege2);
        //When
        List<PrivilegeDto> privilegeDtoList = privilegeMapper.mapToPrivilegeDtoList(privilegeList);
        //Then
        assertEquals(2, privilegeDtoList.size());
        assertEquals("privilegeTest1", privilegeDtoList.get(0).getName());
        assertEquals("privilegeTest2", privilegeDtoList.get(1).getName());
    }

}