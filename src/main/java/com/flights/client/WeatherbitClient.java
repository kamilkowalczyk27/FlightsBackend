package com.flights.client;

import com.flights.config.WeatherbitConfig;
import com.flights.dto.weatherbitDto.WeatherbitCurrentDto;
import com.flights.dto.weatherbitDto.WeatherbitForecastsDto;
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

    public List<WeatherbitForecastsDto> getForecastsByCity() {
        URI url = UriComponentsBuilder.fromHttpUrl(weatherbitConfig.getWeatherbitApiEndpoint())
                .queryParam("city", weatherbitConfig.getWeatherbitApiCity())
                .queryParam("key", weatherbitConfig.getApiKey())
                .queryParam("hours", 24)
                .build().encode().toUri();

        WeatherbitForecastsDto forecastsResponse = restTemplate.getForObject(url, WeatherbitForecastsDto.class);

        if (forecastsResponse != null) {
            List<WeatherbitForecastsDto> result = new ArrayList<>();
            result.add(forecastsResponse);
            return result;
        }
        return new ArrayList<>();
    }
}
