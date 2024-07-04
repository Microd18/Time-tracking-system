package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.entity.Airline;
import com.example.timetrackingsystem.service.AirlineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airline")
@Validated
@RequiredArgsConstructor
@Tag(name = "Авиакомпания")
public class AirlineController {

    private final AirlineService airlineService;

    @PostMapping()
    public ResponseEntity<Airline> addAirline(@RequestParam("name") String name) {
        Airline airline = airlineService.addAirline(name);
        return ResponseEntity.ok(airline);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable("id") Integer id) {
        Airline airline = airlineService.getAirlineById(id);
        return ResponseEntity.ok(airline);
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Airline> getAirlineByName(@PathVariable("name") String name) {
        Airline airline = airlineService.getAirlineByNameIgnoreCase(name);
        return ResponseEntity.ok(airline);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Airline>> getAllAirlines() {
        List<Airline> airline = airlineService.getAllAirlines();
        return ResponseEntity.ok(airline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable("id") Integer id) {
        airlineService.deleteAirlineById(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/{name}")
    public ResponseEntity<Airline> updateAirline(@PathVariable("id") Integer id,
                                                 @PathVariable("name") String name) {
        return ResponseEntity.ok(airlineService.updateAirline(id, name));
    }

}
