package com.flights.controller;

import com.flights.client.WeatherbitClient;
import com.flights.dto.WeatherbitCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1/weatherbit")
public class WeatherbitController {

    @Autowired
    private WeatherbitClient weatherbitClient;

    @RequestMapping(method = RequestMethod.GET, value = "/city")
    public List<WeatherbitCurrentDto> getCurrentWeathetbitByCity() {
        List<WeatherbitCurrentDto> weatherbitByCity = weatherbitClient.getCurrentWeatherByCity();
        return weatherbitByCity;
    }
}
