package com.example.timetrackingsystem.service;

import com.example.timetrackingsystem.enums.ExecutionType;
import org.aspectj.lang.ProceedingJoinPoint;

public interface TimeTracker {

    Object trackTime(ProceedingJoinPoint proceedingJoinPoint, String executionType);
}
