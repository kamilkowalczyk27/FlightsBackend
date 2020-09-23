package com.flights.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeatherbitConfig {

    @Value(value = "${weatherbit.api.key}")
    private String apiKey;

    @Value(value = "${weatherbit.api.city}")
    private String weatherbitApiCity;

    @Value(value = "${weather.api.endpoint.prod}")
    private String weatherbitApiEndpoint;
}
