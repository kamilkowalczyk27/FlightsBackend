package com.flights.schedule;

import com.flights.domain.User;
import com.flights.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}