package com.flights.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenWeatherMapConfig {

    @Value(value = "${open.weather.map.app.key}")
    private String appKey;

    @Value("${open.weather.map.api.endpoint.prod}")
    private String openWeatherOpenApiEndpoint;

    @Value(value = "${open.weather.map.api.city}")
    private String openWeatherApiCity;
}
