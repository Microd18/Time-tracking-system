package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.enums.ExecutionType;
import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.exceptions.ExecutionTimeTrackingException;
import com.example.timetrackingsystem.exceptions.PlaneNotFoundException;
import com.example.timetrackingsystem.service.TimeTracker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TimeTrackerImpl implements TimeTracker {

    private final ExecutionTimeServiceImpl executionTimeService;

    @Override
    public Object trackTime(ProceedingJoinPoint proceedingJoinPoint, String executionType) {
        try {
            long startTime = System.currentTimeMillis();

            Object result = proceedingJoinPoint.proceed();

            long endTime = System.currentTimeMillis();

            long methodExecutionTime = endTime - startTime;

            ExecutionTime executionTime = buildMethodExecutionTimeTracking(proceedingJoinPoint, methodExecutionTime, executionType);
            executionTimeService.saveExecutionTime(executionTime);

            return result;
        } catch (AirlineNotFoundException e) {
            throw new AirlineNotFoundException();
        } catch (PlaneNotFoundException e) {
            throw new PlaneNotFoundException();
        } catch (Throwable e) {
            throw new ExecutionTimeTrackingException();
        }
    }

    private ExecutionTime buildMethodExecutionTimeTracking(ProceedingJoinPoint proceedingJoinPoint, long methodExecutionTime, String executionType) {
        String methodName = proceedingJoinPoint.getSignature().getName();
        return ExecutionTime.builder()
                .methodName(methodName)
                .executionTime(methodExecutionTime)
                .executionType(ExecutionType.valueOf(executionType))
                .build();
    }
}
