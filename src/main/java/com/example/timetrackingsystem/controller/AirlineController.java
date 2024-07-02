package com.example.timetrackingsystem.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/airline")
@Validated
@RequiredArgsConstructor
@Tag(name = "Авиакомпании")
public class AirlineController {
}
