package com.example.timetrackingsystem.service.impl;


import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.repository.ExecutionTimeProjection;
import com.example.timetrackingsystem.repository.ExecutionTimeRepository;
import com.example.timetrackingsystem.service.ExecutionTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ExecutionTimeProjection> getSyncExecutionTimeByMethod(String methodName) {
        return executionTimeRepository.findSyncExecutionTimeByName(methodName);
    }

    @Override
    public List<ExecutionTimeProjection> getAsyncExecutionTimeByMethod(String methodName) {
        return executionTimeRepository.findAsyncExecutionTimeByName(methodName);
    }

    @Override
    public List<ExecutionTime> getAllExecutionTime(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null || pageSize == null) {
            return executionTimeRepository.findAll();
        }

        if (pageSize > 50 || pageSize <= 0) {
            pageSize = 50;
        }
        if (pageNumber < 0) {
            pageNumber = 1;
        }

        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return executionTimeRepository.findAll(pageRequest).getContent();
    }

}
