package com.flights.controller;

import com.flights.domain.Aircraft;
import com.flights.dto.AircraftDto;
import com.flights.exception.AircraftNotFoundException;
import com.flights.facade.AircraftFacade;
import com.flights.mapper.AircraftMapper;
import com.flights.service.AircraftDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class AircraftController {

    @Autowired
    private AircraftDbService aircraftDbService;

    @Autowired
    private AircraftMapper aircraftMapper;

    @Autowired
    private AircraftFacade aircraftFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/aircrafts")
    public List<AircraftDto> getAircrafts() {
        return aircraftMapper.mapToAircraftDtoList(aircraftDbService.getAllAircrafts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/aircrafts/{aircraftId}")
    public AircraftDto getAircraft(@RequestParam Long aircraftId) throws AircraftNotFoundException {
        return aircraftMapper.mapToAircraftDto(aircraftDbService.getAircraft(aircraftId).orElseThrow(AircraftNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/aircrafts", consumes = APPLICATION_JSON_VALUE)
    public void createAircraft(@RequestBody AircraftDto aircraftDto) {
        aircraftDbService.saveAircraft(aircraftMapper.mapToAircraft(aircraftDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "aircrafts")
    public AircraftDto updateAircraft(@RequestBody AircraftDto aircraftDto) {
        return aircraftMapper.mapToAircraftDto(aircraftDbService.saveAircraft(aircraftMapper.mapToAircraft(aircraftDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/aircrafts/{aircraftId}")
    public void deleteAircraft(@RequestParam Long aircraftId) throws AircraftNotFoundException {
        if (aircraftDbService.getAircraft(aircraftId).isPresent())
            aircraftDbService.deleteById(aircraftId);
        else
            throw new AircraftNotFoundException();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAircraftsFacade")
    public List<Aircraft> getAllAircraftsFacade() {
        return aircraftFacade.getAllAircraftsFacade();
    }
}
