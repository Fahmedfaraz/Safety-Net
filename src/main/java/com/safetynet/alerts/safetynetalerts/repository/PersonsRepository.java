package com.safetynet.alerts.safetynetalerts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.safetynet.alerts.safetynetalerts.rest.entity.Persons;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Integer> {

}
