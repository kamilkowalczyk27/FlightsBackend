package com.flights.service;

import com.flights.domain.Flight;
import com.flights.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightDbService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlight(final Long id) {
        return flightRepository.findById(id);
    }

    public Flight saveFlight(final Flight flight) {
        return flightRepository.save(flight);
    }

    public void deleteById(final Long id) {
        flightRepository.deleteById(id);
    }

    public void deleteAllFlights() {
        flightRepository.deleteAll();
    }

    public long countAllFlights() {
        return flightRepository.count();
    }
}
