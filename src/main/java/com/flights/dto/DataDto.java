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
public class DataDto {

    @JsonProperty("city_name")
    private String city_name;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("wind_spd")
    private double wind_spd;

    @JsonProperty("snow")
    private double snow;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("weather")
    private WeatherbitDto weather;
}
