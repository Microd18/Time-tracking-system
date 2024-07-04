package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.entity.Plane;
import com.example.timetrackingsystem.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plane")
@Validated
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @PostMapping()
    public ResponseEntity<Plane> addPlane(@RequestParam("name") String name,
                                          @RequestParam("year") Integer year,
                                          @RequestParam("airlineId") Integer airlineId) {
        Plane plane = planeService.addPlane(name, year, airlineId);
        return ResponseEntity.ok(plane);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable("id") Integer id) {
        Plane plane = planeService.getPlaneById(id);
        return ResponseEntity.ok(plane);
    }

    @GetMapping("by-name/{name}")
    public ResponseEntity<Plane> getPlaneByName(@PathVariable("name") String name) {
        Plane plane = planeService.getPlaneByNameIgnoreCase(name);
        return ResponseEntity.ok(plane);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Plane>> getAllPlanes() {
        List<Plane> planes = planeService.getAllPlanes();
        return ResponseEntity.ok(planes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable("id") Integer id) {
        planeService.deletePlaneById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping()
    public ResponseEntity<Plane> updatePlane(@RequestParam("id") Integer id,
                                             @RequestParam("name") String name,
                                             @RequestParam("year") Integer year,
                                             @RequestParam("airlineId") Integer airlineId) {
        return ResponseEntity.ok(planeService.updatePlane(id, name, year, airlineId));
    }

    @GetMapping("by-airline/{airlineId}")
    public ResponseEntity<List<Plane>> getPlaneByAirlineId(@PathVariable("airlineId") Integer airlineId) {
        List<Plane> planes = planeService.getPlanesByAirlineId(airlineId);
        return ResponseEntity.ok(planes);
    }

}
