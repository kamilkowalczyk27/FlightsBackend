package com.flights.mapper;

import com.flights.domain.TypeOfUser;
import com.flights.dto.TypeOfUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TypeOfUserMapper {

    @Autowired
    private UserMapper userMapper;

    public TypeOfUser mapToTypeOfUser(final TypeOfUserDto typeOfUserDto) {
        return new TypeOfUser(
                typeOfUserDto.getId(),
                typeOfUserDto.getName(),
                userMapper.mapToUserList(typeOfUserDto.getUserDto()));
    }

    public TypeOfUserDto mapToTypeOfUserDto(final TypeOfUser typeOfUser) {
        return new TypeOfUserDto(
                typeOfUser.getId(),
                typeOfUser.getName(),
                userMapper.mapToUserDtoList(typeOfUser.getUsers()));
    }

    public List<TypeOfUser> mapToTypeOfUserList(final List<TypeOfUserDto> typeOfUserDtoList) {
        return typeOfUserDtoList.stream()
                .map(t -> new TypeOfUser(
                        t.getId(),
                        t.getName(),
                        userMapper.mapToUserList(t.getUserDto())))
                .collect(Collectors.toList());
    }

    public List<TypeOfUserDto> mapToTypeOfUserDtoList(final List<TypeOfUser> typeOfUserList) {
        return typeOfUserList.stream()
                .map(t -> new TypeOfUserDto(
                        t.getId(),
                        t.getName(),
                        userMapper.mapToUserDtoList(t.getUsers())))
                .collect(Collectors.toList());
    }
}
