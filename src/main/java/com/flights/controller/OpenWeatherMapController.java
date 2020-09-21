package com.flights.controller;

import com.flights.client.OpenWeatherMapClient;
import com.flights.dto.OpenWeatherMapCurrentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class OpenWeatherMapController {

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    @RequestMapping(method = RequestMethod.GET, value = "/city")
    public void getCurrentWeatherByCity() {
        List<OpenWeatherMapCurrentDto> weatherByCity = openWeatherMapClient.getCurrentWeatherByCity();
    }
}
