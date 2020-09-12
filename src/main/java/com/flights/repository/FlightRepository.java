package com.flights.repository;

import com.flights.domain.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FlightRepository extends CrudRepository<Flight, Long> {
    @Override
    List<Flight> findAll();

    @Override
    Flight save(Flight flight);

    @Override
    Optional<Flight> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
