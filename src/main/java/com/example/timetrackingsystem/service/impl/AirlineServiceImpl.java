package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.model.Airline;
import com.example.timetrackingsystem.repository.AirlineRepository;
import com.example.timetrackingsystem.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public Airline addAirline(String name) {
        return airlineRepository.save(new Airline(name));
    }

    @Override
    public Airline getAirlineById(Integer id) {
        return airlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
    }

    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public void deleteAirlineById(Integer id) {
        airlineRepository.deleteById(id);
    }

    @Override
    public Airline updateAirline(Integer id, String name) {
        Airline airline = airlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
        airline.setAirlineName(name);
        return airlineRepository.save(airline);
    }
    @Override
    public Airline getAirlineByNameIgnoreCase(String name) {
        return airlineRepository.findByAirlineNameIgnoreCase(name).orElseThrow(AirlineNotFoundException::new);
    }

}
