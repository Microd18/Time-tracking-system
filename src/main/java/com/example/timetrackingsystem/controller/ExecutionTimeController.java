package com.example.timetrackingsystem.controller;


import com.example.timetrackingsystem.entity.ExecutionTime;
import com.example.timetrackingsystem.repository.ExecutionTimeProjection;
import com.example.timetrackingsystem.service.ExecutionTimeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для управления запросами, связанными с отслеживанием времени выполнения.
 * <p>
 * Этот контроллер предоставляет REST API для получения информации о времени выполнения синхронных и асинхронных методов,
 * а также для получения всех данных о времени выполнения.
 * </p>
 */

@RestController
@RequestMapping("/track_time")
@Tag(name = "Трекер времени")
@RequiredArgsConstructor
public class ExecutionTimeController {

    private final ExecutionTimeService executionTimeService;

    /**
     * Получает список всех данных о времени выполнения.
     * <p>
     * Метод обрабатывает GET-запрос для получения списка всех данных о времени выполнения с поддержкой пагинации.
     * Параметры запроса `pageNumber` и `pageSize` используются для указания номера страницы и размера страницы соответственно.
     * </p>
     *
     * @param pageNumber номер страницы (необязательный параметр)
     * @param pageSize   размер страницы (необязательный параметр)
     * @return {@link ResponseEntity} со списком данных о времени выполнения
     */
    @GetMapping("/all")
    public ResponseEntity<List<ExecutionTime>> getAllExecutionTime(@RequestParam(name = "pageNumber", required = false) Integer pageNumber,
                                                                   @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        List<ExecutionTime> executionTimes = executionTimeService.getAllExecutionTime(pageNumber, pageSize);
        return ResponseEntity.ok(executionTimes);
    }

    /**
     * Получает данные о времени выполнения методов, выполняемых синхронно, по имени метода.
     * <p>
     * Параметр запроса `name` используется для фильтрации данных по имени метода.
     * </p>
     *
     * @param methodName имя метода (необязательный параметр)
     * @return {@link ResponseEntity} со списком данных о времени выполнения методов, выполняемых синхронно.
     */
    @GetMapping("/sync")
    public ResponseEntity<List<ExecutionTimeProjection>> getSyncExecutionTimeByMethod(@RequestParam(name = "name", required = false) String methodName) {
        List<ExecutionTimeProjection> executionTimes = executionTimeService.getSyncExecutionTimeByMethod(methodName);
        return ResponseEntity.ok(executionTimes);
    }

    /**
     * Получает данные о времени выполнения методов, выполняемых асинхронно, по имени метода.
     * <p>
     * Параметр запроса `name` используется для фильтрации данных по имени метода.
     * </p>
     *
     * @param methodName имя метода (необязательный параметр)
     * @return {@link ResponseEntity} со списком данных о времени выполнения методов, выполняемых асинхронно.
     */
    @GetMapping("/async")
    public ResponseEntity<List<ExecutionTimeProjection>> getAsyncExecutionTimeByMethod(@RequestParam(name = "name", required = false) String methodName) {
        List<ExecutionTimeProjection> executionTimes = executionTimeService.getAsyncExecutionTimeByMethod(methodName);
        return ResponseEntity.ok(executionTimes);
    }
}
