package com.example.timetrackingsystem.service;

import com.example.timetrackingsystem.entity.ExecutionTime;

import java.util.List;

public interface ExecutionTimeService {

    void saveExecutionTime(ExecutionTime executionTime);

    List<ExecutionTime> getAllExecutionTimeByMethod(String methodName);

    List<ExecutionTime> getAllExecutionTime();
}
