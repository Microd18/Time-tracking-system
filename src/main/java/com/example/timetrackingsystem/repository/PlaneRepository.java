package com.example.timetrackingsystem.repository;

import com.example.timetrackingsystem.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaneRepository extends JpaRepository<Plane, Integer> {

    Optional<Plane> findByPlaneNameIgnoreCase(String name);

    List<Plane> findPlanesByAirline_Id(Integer id);
}
