package com.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AircraftDto {
    private String model;
    private BigDecimal maxSpeed;
    private BigDecimal fuelCapacity;
    private BigDecimal maxRange;
    private BigDecimal fuelBurnPerHour;
    private BigDecimal cruisingSpeed;
    private double height;
    private double length;
    private List<FlightDto> flightDtos;
}
