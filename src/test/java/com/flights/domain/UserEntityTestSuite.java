package com.flights.domain;

import com.flights.exception.UserNotFoundException;
import com.flights.repository.UserRepository;
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
public class UserEntityTestSuite {

    @Autowired
    UserRepository userRepository;

    @Test
    public void saveUserTest() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        userRepository.save(user);
        long userId = user.getId();
        //Then
        Assert.assertEquals(1, userId);
        Assert.assertEquals("John", user.getFirstName());
        Assert.assertEquals("Smith", user.getLastName());
        Assert.assertEquals("johnsmith@gmail.com", user.getEmail());
    }

    @Test
    public void readUserTest() throws UserNotFoundException {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        User savedUser = userRepository.save(user);
        long userId = savedUser.getId();
        User resultReadUser = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        //Then
        Assert.assertNotEquals(0, userId);
        Assert.assertEquals("John", resultReadUser.getFirstName());
        Assert.assertEquals("Smith", resultReadUser.getLastName());
        Assert.assertEquals("johnsmith@gmail.com", resultReadUser.getEmail());
    }

    @Test
    public void updateUserTest() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        userRepository.save(user);
        user.setFirstName("Mark");
        user.setLastName("Big");
        user.setEmail("markbig@gmail.com");
        userRepository.save(user);
        //Then
        Assert.assertEquals("Mark", user.getFirstName());
        Assert.assertEquals("Big", user.getLastName());
        Assert.assertEquals("markbig@gmail.com", user.getEmail());
    }

    @Test
    public void deleteUserTest() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        User savedUser = userRepository.save(user);
        long userId = savedUser.getId();
        long countBeforeDelete = userRepository.count();
        userRepository.deleteById(userId);
        long countAfterDelete = userRepository.count();
        //Then
        Assert.assertEquals(countBeforeDelete - 1, countAfterDelete);
    }
}
