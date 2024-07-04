package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.exceptions.PlaneNotFoundException;
import com.example.timetrackingsystem.entity.Airline;
import com.example.timetrackingsystem.entity.Plane;
import com.example.timetrackingsystem.repository.AirlineRepository;
import com.example.timetrackingsystem.repository.PlaneRepository;
import com.example.timetrackingsystem.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final AirlineRepository airlineRepository;

    @Override
    public Plane addPlane(String name, Integer year, Integer airlineId) {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(AirlineNotFoundException::new);
        return planeRepository.save(new Plane(name, year, airline));
    }

    @Override
    public Plane getPlaneById(Integer id) {
        return planeRepository.findById(id).orElseThrow(PlaneNotFoundException::new);
    }

    @Override
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    @Override
    public void deletePlaneById(Integer id) {
        planeRepository.deleteById(id);
    }

    @Override
    public Plane updatePlane(Integer id, String name, Integer year, Integer airlineId) {
        Plane plane = planeRepository.findById(id).orElseThrow(PlaneNotFoundException::new);
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(AirlineNotFoundException::new);
        plane.setPlaneName(name);
        plane.setYearOfManufacture(year);
        plane.setAirline(airline);
        return planeRepository.save(plane);
    }

    @Override
    public Plane getPlaneByNameIgnoreCase(String name) {
        return planeRepository.findByPlaneNameIgnoreCase(name).orElseThrow(PlaneNotFoundException::new);
    }

    @Override
    public List<Plane> getPlanesByAirlineId(Integer airlineId) {
        return planeRepository.findPlanesByAirline_AirlineId(airlineId);
    }
}
