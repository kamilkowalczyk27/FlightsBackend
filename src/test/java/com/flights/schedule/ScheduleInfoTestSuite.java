package com.flights.schedule;

import com.flights.domain.Aircraft;
import com.flights.domain.Flight;
import com.flights.domain.User;
import com.flights.repository.FlightRepository;
import com.flights.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleInfoTestSuite {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleInfo.class);

    @Mock
    private ScheduleInfo scheduleInfo;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Test
    public void sendUserCountInformationTest() {
        //Given
        User user = new User("John", "Smith", "johnsmith@gmail.com", new ArrayList<>(), new ArrayList<>());
        userRepository.save(user);
        long userSize = userRepository.count();
        String userOrUsers = userSize == 1? "user": "users";
        if (userSize != 0) {
            LOGGER.info("Currently in database You got: " + userSize + " " + userOrUsers);
        } else {
            LOGGER.info("User database is empty!");
        }
        //When
        scheduleInfo.sendUserCountInformation();
        //Then
        verify(scheduleInfo, times(1)).sendUserCountInformation();
    }

    @Test
    public void sendFlightCountInformationTest() {
        //Given
        Aircraft aircraft = new Aircraft(1L,"Airbus a320", 11, 37 , new BigDecimal(870), new BigDecimal(30000), new BigDecimal(6150), new BigDecimal(5000), new BigDecimal(828), new ArrayList<>());
        Flight flight = new Flight("Berlin", "London", 2.2, aircraft);
        long flightSize = flightRepository.count();
        String flightOrFlights = flightSize == 1? "user": "users";
        if (flightSize != 0) {
            LOGGER.info("Currently in database You got: " + flightSize + " " + flightOrFlights);
        } else {
            LOGGER.info("Flight database is empty!");
        }
        //When
        scheduleInfo.sendFlightsCountInformation();
        //Then
        verify(scheduleInfo, times(1)).sendFlightsCountInformation();
    }
}