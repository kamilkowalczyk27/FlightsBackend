package com.flights.mapper;

import com.flights.domain.Flight;
import com.flights.dto.FlightDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import java.util.List;

@Component
public class FlightMapper {

    @Autowired
    private AircraftMapper aircraftMapper;

    public Flight mapToFlight(final FlightDto flightDto) {
        return new Flight(
                flightDto.getId(),
                flightDto.getDeparture(),
                flightDto.getArrival(),
                flightDto.getTimeOfFlight(),
                aircraftMapper.mapToAircraft(flightDto.getAircraftDto()));
    }

    public FlightDto mapToFlightDto(final Flight flight) {
        return new FlightDto(
                flight.getId(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getTimeOfFlight(),
                aircraftMapper.mapToAircraftDto(flight.getAircraft()));
    }

    public List<Flight> mapToFlightList(final List<FlightDto> flightDtoList) {
        return flightDtoList.stream()
                .map(t -> new Flight(
                        t.getId(),
                        t.getDeparture(),
                        t.getArrival(),
                        t.getTimeOfFlight(),
                        aircraftMapper.mapToAircraft(t.getAircraftDto())))
                .collect(Collectors.toList());
    }

    public List<FlightDto> mapToFlightDtoList(final List<Flight> flightList) {
        return flightList.stream()
                .map(t -> new FlightDto(
                        t.getId(),
                        t.getDeparture(),
                        t.getArrival(),
                        t.getTimeOfFlight(),
                        aircraftMapper.mapToAircraftDto(t.getAircraft())))
                .collect(Collectors.toList());
    }
}
