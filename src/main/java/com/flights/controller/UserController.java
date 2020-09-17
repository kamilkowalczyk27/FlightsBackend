package com.flights.controller;

import com.flights.dto.UserDto;
import com.flights.exception.UserNotFoundException;
import com.flights.mapper.UserMapper;
import com.flights.service.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDbService userDbService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers() {
        return userMapper.mapToUserDtoList(userDbService.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}")
    public UserDto getUser(@RequestParam Long userId) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.getUser(userId).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        userDbService.saveUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userDbService.saveUser(userMapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{usertId}")
    public void deleteUser(@RequestParam Long userId) throws UserNotFoundException {
        if (userDbService.getUser(userId).isPresent())
            userDbService.deleteById(userId);
        else
            throw new UserNotFoundException();
    }
}
