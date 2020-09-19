package com.flights.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapCurrentDto {

    @JsonProperty("City")
    private String city;

    @JsonProperty("Temperature")
    private double temperature;

    @JsonProperty("Humidity")
    private double humidity;

    @JsonProperty("Pressure")
    private double pressure;

    @JsonProperty("Minimum temperature")
    private double minTemperature;

    @JsonProperty("Maximum temperature")
    private double maxTemperature;

    @JsonProperty("Wind speed")
    private double windSpeed;

    @JsonProperty("Wind direction")
    private double windDirection;
}
