package com.flights.service;

import com.flights.domain.Aircraft;
import com.flights.repository.AircraftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AircraftDbService {

    @Autowired
    private AircraftRepository aircraftRepository;

    public List<Aircraft> getAllAircrafts() {
        return aircraftRepository.findAll();
    }

    public Optional<Aircraft> getAircraft(final Long id) {
        return aircraftRepository.findById(id);
    }

    public Aircraft saveAircraft(final Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft updateAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public void deleteById(final Long id) {
        aircraftRepository.deleteById(id);
    }

    public void deleteAllAircrafts() {
        aircraftRepository.deleteAll();
    }

    public long countAllAircraft() {
        return aircraftRepository.count();
    }
}
