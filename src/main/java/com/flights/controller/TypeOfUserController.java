package com.flights.controller;

import com.flights.dto.TypeOfUserDto;
import com.flights.exception.TypeOfUserNotFoundException;
import com.flights.mapper.TypeOfUserMapper;
import com.flights.service.TypeOfUserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class TypeOfUserController {

    @Autowired
    private TypeOfUserMapper typeOfUserMapper;

    @Autowired
    private TypeOfUserDbService typeOfUserDbService;

    @RequestMapping(method = RequestMethod.GET, value = "/typeOfUsers")
    public List<TypeOfUserDto> getTypeOfUsers() {
        return typeOfUserMapper.mapToTypeOfUserDtoList(typeOfUserDbService.getAllTypeOfUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/typeOfUsers/{typeOfUserId}")
    public TypeOfUserDto getTypeOfUser(@RequestParam Long typeOfUserId) throws TypeOfUserNotFoundException {
        return typeOfUserMapper.mapToTypeOfUserDto(typeOfUserDbService.getTypeOfUser(typeOfUserId).orElseThrow(TypeOfUserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/typeOfUsers", consumes = APPLICATION_JSON_VALUE)
    public void createTypeOfUser(@RequestBody TypeOfUserDto typeOfUserDto) {
        typeOfUserDbService.saveTypeOfUser(typeOfUserMapper.mapToTypeOfUser(typeOfUserDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/typeOfUsers")
    public TypeOfUserDto updateTypeOfUser(@RequestBody TypeOfUserDto typeOfUserDto) {
        return typeOfUserMapper.mapToTypeOfUserDto(typeOfUserDbService.saveTypeOfUser(typeOfUserMapper.mapToTypeOfUser(typeOfUserDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/typeOfUsers/{typeOfUserId}")
    public void deleteTypeOfUser(@RequestParam Long typeOfUserId) throws TypeOfUserNotFoundException {
        if (typeOfUserDbService.getTypeOfUser(typeOfUserId).isPresent())
            typeOfUserDbService.deleteById(typeOfUserId);
        else
            throw new TypeOfUserNotFoundException();
    }
}
