package com.flights.service;

import com.flights.domain.Privilege;
import com.flights.domain.User;
import com.flights.repository.PrivilegeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class PrivilegeDbServiceTestSuite {

    @InjectMocks
    private PrivilegeDbService privilegeDbService;

    @Mock
    private PrivilegeRepository privilegeRepository;

    @Test
    public void getAllPrivilegesTest() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.pl");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Privilege privilege1 = new Privilege(1L,"privilegeTest1", users);
        List<Privilege> privileges = new ArrayList<>();
        privileges.add(privilege1);
        //When
        when(privilegeDbService.getAllPrivileges()).thenReturn(privileges);
        //Then
        assertEquals(1, privileges.size());
    }

    @Test
    public void getPrivilegeTest() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.pl");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Privilege privilege = new Privilege(1L,"privilegeTest1", users);
        Long privilegeIdTest = privilege.getId();
        String privilegeNameTest = privilege.getName();
        //When
        when(privilegeDbService.getPrivilege(privilegeIdTest)).thenReturn(java.util.Optional.of(privilege));
        //Then
        assertEquals("privilegeTest1", privilegeNameTest);
    }

    @Test
    public void savePrivilegeTest() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.pl");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Privilege privilege = new Privilege(1L,"privilegeTest1", users);
        //When
        when(privilegeRepository.save(privilege)).thenReturn(privilege);
        Privilege savedPrivilege = privilegeDbService.savePrivilege(privilege);
        //Then
        assertEquals(privilege.getId(), savedPrivilege.getId());
        assertEquals(privilege.getName(), savedPrivilege.getName());
        assertEquals(privilege.getUsers(), savedPrivilege.getUsers());
    }

    @Test
    public void deleteByIdPrivilegeTest() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.pl");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Privilege privilege = new Privilege(1L,"privilegeTest1", users);
        //When
        privilegeDbService.deleteById(1L);
        //Then
        verify(privilegeRepository, times(1)).deleteById(1L);
    }

    @Test
    public void deleteAllPrivileges() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.pl");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Privilege privilege = new Privilege(1L,"privilegeTest1", users);
        //When
        privilegeDbService.deleteAllPrivileges();
        //Then
        verify(privilegeRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllApivilegesTest() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.pl");
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        Privilege privilege = new Privilege(1L,"privilegeTest1", users);
        long countAllPrivileges = privilegeRepository.count();
        //When
        when(privilegeDbService.countAllPrivileges()).thenReturn(countAllPrivileges);
        //Then
        verify(privilegeRepository, times(1)).count();
    }
}
