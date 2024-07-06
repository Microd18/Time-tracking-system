package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.repository.ExecutionTimeRepository;
import com.example.timetrackingsystem.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    ExecutionTimeRepository executionTimeRepository;

    @Override
    public void saveExecutionTime(ExecutionTime executionTime) {
        executionTimeRepository.save(executionTime);
    }




}
