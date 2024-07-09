package com.example.timetrackingsystem.service;


import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.repository.ExecutionTimeProjection;

import java.util.List;

public interface ExecutionTimeService {

    void saveExecutionTime(ExecutionTime executionTime);

    List<ExecutionTimeProjection> getSyncExecutionTimeByMethod(String methodName);

    List<ExecutionTimeProjection> getAsyncExecutionTimeByMethod(String methodName);

    List<ExecutionTime> getAllExecutionTime(Integer pageNumber, Integer pageSize);
}
