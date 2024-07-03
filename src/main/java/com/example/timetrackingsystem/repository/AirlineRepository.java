package com.example.timetrackingsystem.repository;

import com.example.timetrackingsystem.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {

    Optional<Airline> findByAirlineNameIgnoreCase(String name);
}
