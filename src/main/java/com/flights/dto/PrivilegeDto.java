package com.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeDto {
    private Long id;
    private String name;
    private List<UserDto> userDtos;
}
