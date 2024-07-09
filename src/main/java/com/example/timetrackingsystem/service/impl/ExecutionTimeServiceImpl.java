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

/**
 * Реализация сервиса для управления сущностями {@link ExecutionTime}.
 * <p>
 * Этот сервис предоставляет методы для сохранения и получения информации о времени выполнения методов,
 * как в синхронном, так и в асинхронном режимах.
 * </p>
 */
@Service
@Transactional
@RequiredArgsConstructor
public class ExecutionTimeServiceImpl implements ExecutionTimeService {

    private final ExecutionTimeRepository executionTimeRepository;

    /**
     * Асинхронно сохраняет объект {@link ExecutionTime} в репозитории.
     *
     * @param executionTime объект, время выполнения которого сохраняется в БД.
     */
    @Override
    @Async
    public void saveExecutionTime(ExecutionTime executionTime) {
        executionTimeRepository.save(executionTime);
    }

    /**
     * Получает список времени выполнения методов, выполняемых синхронно, по имени метода.
     *
     * @param methodName имя метода для поиска времени выполнения
     * @return список {@link ExecutionTimeProjection}
     */
    @Override
    public List<ExecutionTimeProjection> getSyncExecutionTimeByMethod(String methodName) {
        return executionTimeRepository.findSyncExecutionTimeByName(methodName);
    }

    /**
     * Получает список времени выполнения методов, выполняемых асинхронно, по имени метода.
     *
     * @param methodName имя метода для поиска времени выполнения
     * @return список {@link ExecutionTimeProjection}
     */
    @Override
    public List<ExecutionTimeProjection> getAsyncExecutionTimeByMethod(String methodName) {
        return executionTimeRepository.findAsyncExecutionTimeByName(methodName);
    }

    /**
     * Получает все записи времени выполнения с поддержкой постраничного вывода.
     * <p>
     * Если параметры постраничного вывода не заданы, возвращает все записи.
     * Максимальный размер страницы установлен на 50 записей.
     * Если номер страницы меньше 1, то используется первая страница.
     * </p>
     *
     * @param pageNumber номер страницы (начиная с 1)
     * @param pageSize   размер страницы
     * @return список {@link ExecutionTime} для указанного диапазона
     */
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
