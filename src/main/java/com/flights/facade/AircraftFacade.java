package com.flights.facade;

import com.flights.domain.Aircraft;
import com.flights.dto.AircraftDto;
import com.flights.mapper.AircraftMapper;
import com.flights.service.AircraftDbService;
import com.flights.validator.AircraftValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AircraftFacade {

    @Autowired
    private AircraftDbService aircraftDbService;
    @Autowired
    private AircraftMapper aircraftMapper;
    @Autowired
    private AircraftValidator aircraftValidator;

    public List<Aircraft> getAllAircraftsFacade() {
        List<AircraftDto> aircraftDtoList = aircraftMapper.mapToAircraftDtoList(aircraftDbService.getAllAircrafts());
        List<AircraftDto> filteredAircraft = aircraftValidator.validateAircrafts(aircraftDtoList);
        return aircraftMapper.mapToAircraftList(filteredAircraft);

    }
}
