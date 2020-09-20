package com.flights.client;

import com.flights.config.OpenWeatherMapConfig;
import com.flights.dto.OpenWeatherMapCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class OpenWeatherMapClient {

    @Autowired
    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    private RestTemplate restTemplate;


    public OpenWeatherMapCurrentDto getCurrentWeatherByCity() {
        URI url = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfig.getOpenWeatherOpenApiEndpoint())
                .queryParam("q", openWeatherMapConfig.getOpenWeatherApiCity())
                .queryParam("key", openWeatherMapConfig.getAppKey())
                .build().encode().toUri();

        OpenWeatherMapCurrentDto weatherResponse = restTemplate.getForObject(url, OpenWeatherMapCurrentDto.class);

        return new OpenWeatherMapCurrentDto();
    }
}
