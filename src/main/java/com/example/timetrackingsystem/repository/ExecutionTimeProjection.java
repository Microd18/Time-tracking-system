package com.example.timetrackingsystem.repository;

import com.example.timetrackingsystem.enums.ExecutionType;

public interface ExecutionTimeProjection {
    String getMethodName();

    Long getAverageExecutionTime();

    Long getTotalExecutionTime();

    ExecutionType getExecutionType();
}
