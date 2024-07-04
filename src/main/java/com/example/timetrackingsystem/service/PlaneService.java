package com.example.timetrackingsystem.service;

import com.example.timetrackingsystem.entity.Plane;

import java.util.List;

public interface PlaneService {

    Plane addPlane(String name, Integer year, Integer airlineId);

    Plane getPlaneById(Integer id);

    List<Plane> getAllPlanes();

    void deletePlaneById(Integer id);

    Plane updatePlane(Integer id, String name, Integer year, Integer airlineId);

    Plane getPlaneByNameIgnoreCase(String name);

    List<Plane> getPlanesByAirlineId(Integer airlineId);
}
