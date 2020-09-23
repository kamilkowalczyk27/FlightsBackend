package com.flights.client;

import com.flights.config.OpenWeatherMapConfig;
import com.flights.dto.openWeatherMapDto.OpenWeatherMapCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class OpenWeatherMapClient {

    @Autowired
    private OpenWeatherMapConfig openWeatherMapConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<OpenWeatherMapCurrentDto> getCurrentWeatherByCity() {
        URI url = UriComponentsBuilder.fromHttpUrl(openWeatherMapConfig.getOpenWeatherOpenApiEndpoint())
                .queryParam("q", openWeatherMapConfig.getOpenWeatherApiCity() )
                .queryParam("appid", openWeatherMapConfig.getAppKey())
                .build().encode().toUri();

        OpenWeatherMapCurrentDto weatherResponse = restTemplate.getForObject(url, OpenWeatherMapCurrentDto.class);

        if (weatherResponse != null) {
            List<OpenWeatherMapCurrentDto> result = new ArrayList<>();
            result.add(weatherResponse);
            return result;
        }
        return new ArrayList<>();
    }
}
