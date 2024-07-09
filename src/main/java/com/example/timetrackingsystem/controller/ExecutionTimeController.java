package com.example.timetrackingsystem.controller;


import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.repository.ExecutionTimeProjection;
import com.example.timetrackingsystem.service.ExecutionTimeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time_track")
@Tag(name = "Трекер времени")
@RequiredArgsConstructor
public class ExecutionTimeController {

    private final ExecutionTimeService executionTimeService;

    @GetMapping("/all")
    public ResponseEntity<List<ExecutionTime>> getAllExecutionTime(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                                                   @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        List<ExecutionTime> executionTimes = executionTimeService.getAllExecutionTime(pageNumber, pageSize);
        return ResponseEntity.ok(executionTimes);
    }

    @GetMapping("/sync")
    public ResponseEntity<List<ExecutionTimeProjection>> getSyncExecutionTimeByMethod(@RequestParam(name = "name", required = false) String methodName) {
        List<ExecutionTimeProjection> executionTimes = executionTimeService.getSyncExecutionTimeByMethod(methodName);
        return ResponseEntity.ok(executionTimes);
    }

    @GetMapping("/async")
    public ResponseEntity<List<ExecutionTimeProjection>> getAsyncExecutionTimeByMethod(@RequestParam(name = "name", required = false) String methodName) {
        List<ExecutionTimeProjection> executionTimes = executionTimeService.getAsyncExecutionTimeByMethod(methodName);
        return ResponseEntity.ok(executionTimes);
    }
}
