package com.flights.dto.weatherbitDto;

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
public class DataForecastsDto {

    @JsonProperty("city_name")
    private String city_name;

    @JsonProperty("snow_depth")
    private double snow_depth;

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("wind_spd")
    private double wind_spd;

    @JsonProperty("weather")
    private WeatherbitDto weather;
}
