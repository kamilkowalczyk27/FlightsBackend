package com.flights.client;

import com.flights.config.WeatherbitConfig;
import com.flights.dto.WeatherbitCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherbitClient {

    @Autowired
    private WeatherbitConfig weatherbitConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<WeatherbitCurrentDto> getCurrentWeatherByCity() {
        URI url = UriComponentsBuilder.fromHttpUrl(weatherbitConfig.getWeatherbitApiEndpoint())
                .queryParam("city", weatherbitConfig.getWeatherbitApiCity())
                .queryParam("key", weatherbitConfig.getApiKey())
                .build().encode().toUri();

        WeatherbitCurrentDto weatherResponse = restTemplate.getForObject(url, WeatherbitCurrentDto.class);

        if (weatherResponse != null) {
            List<WeatherbitCurrentDto> result = new ArrayList<>();
            result.add(weatherResponse);
            return result;
        }
        return new ArrayList<>();
    }
}
