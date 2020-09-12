package com.flights.repository;

import com.flights.domain.Aircraft;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AircraftRepository extends CrudRepository<Aircraft, Long> {
    @Override
    List<Aircraft> findAll();

    @Override
    Aircraft save(Aircraft aircraft);

    @Override
    Optional<Aircraft> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
