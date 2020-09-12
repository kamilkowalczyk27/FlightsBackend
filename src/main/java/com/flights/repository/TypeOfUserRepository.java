package com.flights.repository;

import com.flights.domain.TypeOfUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TypeOfUserRepository extends CrudRepository<TypeOfUser, Long> {
    @Override
    List<TypeOfUser> findAll();

    @Override
    TypeOfUser save(TypeOfUser typeOfUser);

    @Override
    Optional<TypeOfUser> findById(Long id);

    @Override
    void deleteById(Long id);

    @Override
    long count();
}
