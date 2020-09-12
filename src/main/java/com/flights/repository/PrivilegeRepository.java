package com.flights.repository;

import com.flights.domain.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    @Override
    List<Privilege> findAll();

    @Override
    Privilege save(Privilege privilege);

    @Override
    Optional<Privilege> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
