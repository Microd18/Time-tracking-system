package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.repository.ExecutionTimeRepository;
import com.example.timetrackingsystem.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    @Override
    @Async
    public void saveExecutionTime(ExecutionTime executionTime) {
        executionTimeRepository.save(executionTime);
    }

    @Override
    public List<ExecutionTime> getAllExecutionTimeByMethod(String methodName) {
        return executionTimeRepository.findAllExecutionTimeByName(methodName);
    }

    @Override
    public List<ExecutionTime> getAllExecutionTime() {
        return executionTimeRepository.findAll();
    }

}
