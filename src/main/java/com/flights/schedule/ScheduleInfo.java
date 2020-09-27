package com.flights.schedule;

import com.flights.repository.FlightRepository;
import com.flights.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduleInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleInfo.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendUserCountInformation() {
        long userSize = userRepository.count();
        String userOrUsers = userSize == 1? "user": "users";
        if (userSize != 0) {
            LOGGER.info("Currently in database You got: " + userSize + " " + userOrUsers);
        } else {
            LOGGER.info("User database is empty!");
        }
    }

    @Scheduled(cron = "0 0 10 * * *")
    //@Scheduled(fixedDelay = 10000)
    public void sendFlightsCountInformation() {
        long flightSize = flightRepository.count();
        String flightOrFlights = flightSize == 1? "flight": "flights";
        if (flightSize != 0) {
            LOGGER.info("Currently in database You got: " + flightSize + " " + flightOrFlights);
        } else {
            LOGGER.info("Flight database is empty!");
        }
    }
}
