package com.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenWeatherMapCurrent {
    private String city;
    private double temperature;
    private double humidity;
    private double pressure;
    private double minTemperature;
    private double maxTemperature;
    private double windSpeed;
    private double windDirection;
}
