package com.flights.service;

import com.flights.domain.User;
import com.flights.repository.UserRepository;
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
public class UserDbServiceTestSuite {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void getAllUsersTet() {
        //Given
        User user1 = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(2L,"Mark", "Big", "markbig@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //When
        when(userDbService.getAllUsers()).thenReturn(users);
        //Then
        assertEquals(2, users.size());
    }

    @Test
    public void getUserTest() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        Long userIdTest = user.getId();
        String userFirstNameTest = user.getFirstName();
        //When
        when(userDbService.getUser(userIdTest)).thenReturn(java.util.Optional.of(user));
        //Then
        assertEquals("John", userFirstNameTest);
    }

    @Test
    public void saveUserTest() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userDbService.saveUser(user);
        //Then
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getEmail(), savedUser.getEmail());
    }

    @Test
    public void deleteByIdUserTest() {
        //Given
        User user1 = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(2L,"Mark", "Big", "markbig@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //When
        userDbService.deleteById(2L);
        //Then
        verify(userRepository, times(1)).deleteById(2L);
    }

    @Test
    public void deleteAllUsers() {
        //Given
        User user1 = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(2L,"Mark", "Big", "markbig@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        //When
        userDbService.deleteAllUsers();
        //Then
        verify(userRepository, times(1)).deleteAll();
    }

    @Test
    public void countAllUsers() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        userRepository.save(user);
        long countAllUsers = userRepository.count();
        //When
        when(userDbService.countAllUsers()).thenReturn(countAllUsers);
        //Then
        verify(userRepository, times(1)).count();
    }
}