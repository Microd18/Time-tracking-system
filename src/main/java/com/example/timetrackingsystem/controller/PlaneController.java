package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plane")
@Validated
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

}
