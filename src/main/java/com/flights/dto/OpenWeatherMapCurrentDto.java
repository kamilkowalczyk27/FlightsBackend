package com.flights.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherMapCurrentDto {

//    @JsonProperty("name")
//    private String name;
//
//    @JsonProperty("country")
//    private String country;
//
//    @JsonProperty("weather_description")
//    private String weatherDescription;
//
//    @JsonProperty("temp")
//    private double temp;
//
//    @JsonProperty("feels_like")
//    private double feels_like;
//
//    @JsonProperty("temp_min")
//    private double temp_min;
//
//    @JsonProperty("temp_max")
//    private double temp_max;
//
//    @JsonProperty("pressure")
//    private double pressure;
//
//    @JsonProperty("humidity")
//    private double humidity;
//
//    @JsonProperty("windSpeed")
//    private double windSpeed;
//
//    @JsonProperty("cloudsAll")
//    private double cloudsAll;

    @JsonProperty("name")
    private String name;

}
