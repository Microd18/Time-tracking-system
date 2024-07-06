package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.service.ExecutionTimeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time_track")
@Tag(name = "Трекер времени")
@RequiredArgsConstructor
public class ExecutionTimeController {

    private final ExecutionTimeService executionTimeService;

    @GetMapping("/all")
    public ResponseEntity<List<ExecutionTime>> getAllExecutionTime() {
        List<ExecutionTime> executionTimes = executionTimeService.getAllExecutionTime();
        return ResponseEntity.ok(executionTimes);
    }

    @GetMapping()
    public ResponseEntity<List<ExecutionTime>> getAllExecutionTimeByMethod(@RequestParam(name = "name") String methodName) {
        List<ExecutionTime> executionTimes = executionTimeService.getAllExecutionTimeByMethod(methodName);
        return ResponseEntity.ok(executionTimes);
    }
}
