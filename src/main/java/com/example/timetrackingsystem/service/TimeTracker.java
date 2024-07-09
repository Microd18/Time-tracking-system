package com.example.timetrackingsystem.service;

import org.aspectj.lang.ProceedingJoinPoint;

public interface TimeTracker {

    Object trackTime(ProceedingJoinPoint proceedingJoinPoint, String executionType);
}
