package com.flights.validator;

import com.flights.dto.AircraftDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AircraftValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(AircraftValidator.class);

    public List<AircraftDto> validateAircrafts(final List<AircraftDto> aircraftList) {
        LOGGER.info("Method started validateGetAllAircrafts in AircraftValidator");
        List<AircraftDto> filteredAircraft = aircraftList.stream()
                .filter(aircraft -> !aircraft.getModel().equalsIgnoreCase("testModel"))
                .collect(Collectors.toList());
        LOGGER.info("Method ended validateGetAllAircrafts in AircraftValidator, Motives size: " + filteredAircraft.size());
        return filteredAircraft;
    }
}
