package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.model.Plane;
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

    @GetMapping("/{name}")
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

    @PatchMapping("/{id}/{name}")
    public ResponseEntity<Plane> updatePlane(@PathVariable("id") Integer id,
                                             @PathVariable("name") String name,
                                             @PathVariable("id") Integer year) {
        return ResponseEntity.ok(planeService.updatePlane(id, name, year));
    }

}
