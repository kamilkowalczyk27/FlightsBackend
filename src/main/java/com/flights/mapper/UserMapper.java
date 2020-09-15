package com.flights.mapper;

import com.flights.domain.User;
import com.flights.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private TypeOfUserMapper typeOfUserMapper;

    @Autowired
    private PrivilegeMapper privilegeMapper;

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail(),
                typeOfUserMapper.mapToTypeOfUserList(userDto.getTypeOfUserDto()),
                privilegeMapper.mapToPrivilegeList(userDto.getPrivilegeDto()));
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                typeOfUserMapper.mapToTypeOfUserDtoList(user.getTypeOfUsers()),
                privilegeMapper.mapToPrivilegeDtoList(user.getPrivileges()));
    }

    public List<User> mapToUserList(final List<UserDto> userDtoList) {
        return userDtoList.stream()
                .map(t -> new User(
                        t.getId(),
                        t.getFirstName(),
                        t.getLastName(),
                        t.getEmail(),
                        typeOfUserMapper.mapToTypeOfUserList(t.getTypeOfUserDto()),
                        privilegeMapper.mapToPrivilegeList(t.getPrivilegeDto())))
                .collect(Collectors.toList());
    }


    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(t -> new UserDto(
                        t.getId(),
                        t.getFirstName(),
                        t.getLastName(),
                        t.getEmail(),
                        typeOfUserMapper.mapToTypeOfUserDtoList(t.getTypeOfUsers()),
                        privilegeMapper.mapToPrivilegeDtoList(t.getPrivileges())))
                .collect(Collectors.toList());
    }
}
