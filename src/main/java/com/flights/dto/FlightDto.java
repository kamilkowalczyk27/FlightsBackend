package com.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private String departure;
    private String arrival;
    private double timeOfFlight;
}
