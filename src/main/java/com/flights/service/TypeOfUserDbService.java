package com.flights.service;

import com.flights.domain.TypeOfUser;
import com.flights.repository.TypeOfUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TypeOfUserDbService {

    @Autowired
    private TypeOfUserRepository typeOfUserRepository;

    public List<TypeOfUser> getAllTypeOfUsers() {
        return typeOfUserRepository.findAll();
    }

    public Optional<TypeOfUser> getTypeOfUser(final Long id) {
        return typeOfUserRepository.findById(id);
    }

    public TypeOfUser saveTypeOfUser(final TypeOfUser typeOfUser) {
        return typeOfUserRepository.save(typeOfUser);
    }

    public TypeOfUser updateTypeOfUser(TypeOfUser typeOfUser) {
        return typeOfUserRepository.save(typeOfUser);
    }

    public void deleteById(final Long id) {
        typeOfUserRepository.deleteById(id);
    }

    public void deleteAllTypeOfUsers() {
        typeOfUserRepository.deleteAll();
    }

    public long countAllTypeOfUsers() {
        return typeOfUserRepository.count();
    }
}
