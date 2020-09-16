package com.flights.mapper;

import com.flights.domain.User;
import com.flights.dto.UserDto;
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
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void mapToUserTest() {
        //Given
        UserDto userDto = new UserDto(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        User user = userMapper.mapToUser(userDto);
        //Then
        assertEquals(1, (long)user.getId());
        assertEquals("John", user.getFirstName());
        assertEquals("Smith", user.getLastName());
        assertEquals("johnsmith@gmail.com", user.getEmail());
    }

    @Test
    public void mapToUserDtoTest() {
        //Given
        User user = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        //When
        UserDto userDto = userMapper.mapToUserDto(user);
        //Then
        assertEquals(1, (long)userDto.getId());
        assertEquals("John", userDto.getFirstName());
        assertEquals("Smith", userDto.getLastName());
        assertEquals("johnsmith@gmail.com", userDto.getEmail());
    }

    @Test
    public void mapToUserListTest() {
        //Given
        UserDto userDto1 = new UserDto(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        UserDto userDto2 = new UserDto(2L,"Mark", "Big", "markbig@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto1);
        userDtoList.add(userDto2);
        //When
        List<User> userList = userMapper.mapToUserList(userDtoList);
        //Then
        assertEquals(2, userList.size());
        assertEquals("John", userList.get(0).getFirstName());
        assertEquals("Mark", userList.get(1).getFirstName());
    }

    @Test
    public void mapToUserDtoListTest() {
        //Given
        User user1 = new User(1L, "John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(2L,"Mark", "Big", "markbig@gmail.com", new ArrayList<>(), new ArrayList<>());
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        //When
        List<UserDto> userDtoList = userMapper.mapToUserDtoList(userList);
        //Then
        assertEquals(2, userDtoList.size());
        assertEquals("John", userDtoList.get(0).getFirstName());
        assertEquals("Mark", userDtoList.get(1).getFirstName());
    }
}
