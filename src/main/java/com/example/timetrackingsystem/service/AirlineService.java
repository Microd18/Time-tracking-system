package com.example.timetrackingsystem.service;

import com.example.timetrackingsystem.model.Airline;

import java.util.List;

public interface AirlineService {

    Airline addAirline(String name);

    Airline getAirlineById(Integer id);

    List<Airline> getAllAirlines();

    void deleteAirlineById(Integer id);

    Airline updateAirline(Integer id, String name);

    Airline getAirlineByNameIgnoreCase(String name);
}
