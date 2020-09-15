package com.flights.mapper;

import com.flights.domain.Aircraft;
import com.flights.dto.AircraftDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AircraftMapper {

    @Autowired
    private FlightMapper flightMapper;

    public Aircraft mapToAircraft(final AircraftDto aircraftDto) {
        return new Aircraft(
                aircraftDto.getId(),
                aircraftDto.getModel(),
                aircraftDto.getHeight(),
                aircraftDto.getLength(),
                aircraftDto.getMaxSpeed(),
                aircraftDto.getFuelCapacity(),
                aircraftDto.getMaxRange(),
                aircraftDto.getFuelBurnPerHour(),
                aircraftDto.getCruisingSpeed(),
                flightMapper.mapToFlightList(aircraftDto.getFlightDtoList()));
    }

    public AircraftDto mapToAircraftDto(final Aircraft aircraft) {
        return new AircraftDto(
                aircraft.getId(),
                aircraft.getModel(),
                aircraft.getHeight(),
                aircraft.getLength(),
                aircraft.getMaxSpeed(),
                aircraft.getFuelCapacity(),
                aircraft.getMaxRange(),
                aircraft.getFuelBurnPerHour(),
                aircraft.getCruisingSpeed(),
                flightMapper.mapToFlightDtoList(aircraft.getFlights()));
    }

    public List<Aircraft> mapToAircraftList(final List<AircraftDto> aircraftDtoList) {
        return aircraftDtoList.stream()
                .map(t -> new Aircraft(
                        t.getId(),
                        t.getModel(),
                        t.getHeight(),
                        t.getLength(),
                        t.getMaxSpeed(),
                        t.getFuelCapacity(),
                        t.getMaxRange(),
                        t.getFuelBurnPerHour(),
                        t.getCruisingSpeed(),
                        flightMapper.mapToFlightList(t.getFlightDtoList())))
                .collect(Collectors.toList());
    }

    public List<AircraftDto> mapToAircraftDtoList(final List<Aircraft> aircraftList) {
        return aircraftList.stream()
                .map(t -> new AircraftDto(
                        t.getId(),
                        t.getModel(),
                        t.getHeight(),
                        t.getLength(),
                        t.getMaxSpeed(),
                        t.getFuelCapacity(),
                        t.getMaxRange(),
                        t.getFuelBurnPerHour(),
                        t.getCruisingSpeed(),
                        flightMapper.mapToFlightDtoList(t.getFlights())))
                .collect(Collectors.toList());
    }
}
