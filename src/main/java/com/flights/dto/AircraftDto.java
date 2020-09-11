package com.flights.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

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
}
