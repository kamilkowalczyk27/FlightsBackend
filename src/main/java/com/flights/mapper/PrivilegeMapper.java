package com.flights.mapper;

import com.flights.domain.Privilege;
import com.flights.dto.PrivilegeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrivilegeMapper {

    @Autowired
    private UserMapper userMapper;

    public Privilege mapToPrivilege(final PrivilegeDto privilegeDto) {
        return new Privilege(
                privilegeDto.getId(),
                privilegeDto.getName(),
                userMapper.mapToUserList(privilegeDto.getUserDtos()));
    }

    public PrivilegeDto mapToPrivilegeDto(final Privilege privilege) {
        return new PrivilegeDto(
                privilege.getId(),
                privilege.getName(),
                userMapper.mapToUserDtoList(privilege.getUsers()));
    }

    public List<Privilege> mapToPrivilegeList(final List<PrivilegeDto> privilegeDtoList) {
        return privilegeDtoList.stream()
                .map(t -> new Privilege(
                        t.getId(),
                        t.getName(),
                        userMapper.mapToUserList(t.getUserDtos())))
                .collect(Collectors.toList());
    }

    public List<PrivilegeDto> mapToPrivilegeDtoList(final List<Privilege> privilegeList) {
        return privilegeList.stream()
                .map(t -> new PrivilegeDto(
                        t.getId(),
                        t.getName(),
                        userMapper.mapToUserDtoList(t.getUsers())))
                .collect(Collectors.toList());
    }
}
