package com.flights.client;

import com.flights.config.OpenWeatherMapConfig;
import com.flights.dto.OpenWeatherMapCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@Component
public class OpenWeatherMapClient {

    @Value(value = "${open.weather.map.app.key}")
    private String appKey;

    @Value(value = "${open.weather.map.api.endpoint.prod}")
    private String openWeatherOpenApiEndpoint;

    @Value("${open.weather.map.api.city}")
    private String openWeatherOpenCity;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OpenWeatherMapConfig openWeatherMapConfig;

    public OpenWeatherMapCurrentDto getCurrentWeatherByCity(String city) {

        URI url = UriComponentsBuilder.fromHttpUrl(openWeatherOpenApiEndpoint + "/api.openweathermap.org/data/2.5/weather")
                .queryParam("q", openWeatherOpenCity)
                .queryParam("key", appKey).build().encode().toUri();

        OpenWeatherMapCurrentDto weatherResponse = restTemplate.getForObject(url, OpenWeatherMapCurrentDto.class);

        return new OpenWeatherMapCurrentDto();
    }
}
