package com.flights.controller;

import com.flights.dto.FlightDto;
import com.flights.exception.FlightNotFoundException;
import com.flights.mapper.FlightMapper;
import com.flights.service.FlightDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/")
public class FlightController {

    @Autowired
    private FlightDbService flightDbService;

    @Autowired
    private FlightMapper flightMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/flights")
    public List<FlightDto> getFlights() {
        return flightMapper.mapToFlightDtoList(flightDbService.getAllFlights());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flights/{flightId}")
    public FlightDto getFlight(@RequestParam Long flightId) throws FlightNotFoundException {
        return flightMapper.mapToFlightDto(flightDbService.getFlight(flightId).orElseThrow(FlightNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "flights", consumes = APPLICATION_JSON_VALUE)
    public void createFlight(@RequestBody FlightDto flightDto) {
        flightDbService.saveFlight(flightMapper.mapToFlight(flightDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "flights")
    public FlightDto updateFlight(@RequestBody FlightDto flightDto) {
        return flightMapper.mapToFlightDto(flightDbService.saveFlight(flightMapper.mapToFlight(flightDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/flights/{flightId}")
    public void deleteFlight(@RequestParam Long flightId) throws FlightNotFoundException {
        if (flightDbService.getFlight(flightId).isPresent())
            flightDbService.deleteById(flightId);
        else
            throw new FlightNotFoundException();
    }
}
