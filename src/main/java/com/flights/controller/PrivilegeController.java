package com.flights.controller;

import com.flights.dto.PrivilegeDto;
import com.flights.exception.PrivilegeNotFoundException;
import com.flights.mapper.PrivilegeMapper;
import com.flights.service.PrivilegeDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class PrivilegeController {

    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Autowired
    private PrivilegeDbService privilegeDbService;

    @RequestMapping(method = RequestMethod.GET, value = "/privileges")
    public List<PrivilegeDto> getPrivileges() {
        return privilegeMapper.mapToPrivilegeDtoList(privilegeDbService.getAllPrivileges());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/privileges/{privilegeId}")
    public PrivilegeDto getPrivilege(@RequestParam Long privilegeId) throws PrivilegeNotFoundException {
        return privilegeMapper.mapToPrivilegeDto(privilegeDbService.getPrivilege(privilegeId).orElseThrow(PrivilegeNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/privileges", consumes = APPLICATION_JSON_VALUE)
    public void createPrivilege(@RequestBody PrivilegeDto privilegeDto) {
        privilegeDbService.savePrivilege(privilegeMapper.mapToPrivilege(privilegeDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/privileges")
    public PrivilegeDto updatePrivilege(@RequestBody PrivilegeDto privilegeDto) {
        return privilegeMapper.mapToPrivilegeDto(privilegeDbService.savePrivilege(privilegeMapper.mapToPrivilege(privilegeDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/privileges/{privilegeId}")
    public void deletePrivilege(@RequestParam Long privilegeId) throws PrivilegeNotFoundException {
        if (privilegeDbService.getPrivilege(privilegeId).isPresent())
            privilegeDbService.deleteById(privilegeId);
        else
            throw new PrivilegeNotFoundException();
    }
}
