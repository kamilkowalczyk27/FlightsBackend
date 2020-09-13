package com.flights.service;

import com.flights.domain.Privilege;
import com.flights.repository.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrivilegeDbService {

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public List<Privilege> getAllPrivileges() {
        return privilegeRepository.findAll();
    }

    public Optional<Privilege> getPrivilege(final Long id) {
        return privilegeRepository.findById(id);
    }

    public Privilege savePrivilege(final Privilege privilege) {
        return privilegeRepository.save(privilege);
    }

    public void deleteById(final Long id) {
        privilegeRepository.deleteById(id);
    }

    public void deleteAllPrivileges() {
        privilegeRepository.deleteAll();
    }

    public long countAllPrivileges() {
        return privilegeRepository.count();
    }
}
