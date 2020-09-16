package com.flights.domain;

import com.flights.exception.PrivilegeNotFoundException;
import com.flights.repository.PrivilegeRepository;
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
public class PrivilegeEntityTestSuite {

    @Autowired
    PrivilegeRepository privilegeRepository;

    @Test
    public void savePrivilegeTest() {
        //Given
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        //When
        privilegeRepository.save(privilege);
        long privilegeId = privilege.getId();
        //Then
        Assert.assertEquals(1, privilegeId);
        Assert.assertEquals("privilegeTest", privilege.getName());
    }

    @Test
    public void readPrivilegeTest() throws PrivilegeNotFoundException {
        //Given
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        //When
        Privilege savedPrivilege = privilegeRepository.save(privilege);
        long privilegeId = savedPrivilege.getId();
        Privilege resultReadPrivilege = privilegeRepository.findById(privilegeId).orElseThrow(PrivilegeNotFoundException::new);
        //Then
        Assert.assertEquals("privilegeTest", resultReadPrivilege.getName());
    }

    @Test
    public void updatePrivilegeTest() {
        //Given
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        //When
        privilegeRepository.save(privilege);
        privilege.setName("privilegeUpdatedTest");
        privilegeRepository.save(privilege);
        //Then
        Assert.assertEquals("privilegeUpdatedTest", privilege.getName());
    }

    @Test
    public void deletePrivilegeTest() {
        //Given
        Privilege privilege = new Privilege(1L,"privilegeTest", new ArrayList<>());
        //When
        Privilege savedPrivilege = privilegeRepository.save(privilege);
        long privilegeId = savedPrivilege.getId();
        long countBeforeDelete = privilegeRepository.count();
        privilegeRepository.deleteById(privilegeId);
        long countAfterDelete = privilegeRepository.count();
        //Then
        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);
    }
}
