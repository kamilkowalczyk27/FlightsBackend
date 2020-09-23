package com.flights.dto.openWeatherMapDto;

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

    @JsonProperty("name")
    private String name;

    @JsonProperty("sys")
    private SysDto sysDto;

    @JsonProperty("weather")
    private List<WeatherDto> weathers;

    @JsonProperty("main")
    private MainDto mainDto;

    @JsonProperty("wind")
    private WindDto windDto;
}
