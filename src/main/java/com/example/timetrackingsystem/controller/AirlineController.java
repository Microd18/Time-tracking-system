package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.entity.Airline;
import com.example.timetrackingsystem.service.AirlineService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления запросами, связанными с авиакомпаниями.
 * <p>
 * Этот контроллер предоставляет REST API для выполнения операций над сущностями {@link Airline}.
 * </p>
 */

@RestController
@RequestMapping("/airline")
@RequiredArgsConstructor
@Tag(name = "Авиакомпания")
public class AirlineController {

    private final AirlineService airlineService;

    /**
     * Добавляет новую авиакомпанию.
     * <p>
     * Метод обрабатывает POST-запрос на добавление новой авиакомпании. Имя авиакомпании передается в параметре запроса.
     * </p>
     *
     * @param name имя авиакомпании
     * @return {@link ResponseEntity} с созданной авиакомпанией
     */

    @PostMapping()
    public ResponseEntity<Airline> addAirline(@RequestParam("name") String name) {
        Airline airline = airlineService.addAirline(name);
        return ResponseEntity.ok(airline);
    }

    /**
     * Получает информацию об авиакомпании по её идентификатору.
     * <p>
     * Метод обрабатывает GET-запрос для получения авиакомпании по её идентификатору.
     * </p>
     *
     * @param id идентификатор авиакомпании
     * @return {@link ResponseEntity} с найденной авиакомпанией
     */
    @GetMapping("/{id}")
    public ResponseEntity<Airline> getAirlineById(@PathVariable("id") Integer id) {
        Airline airline = airlineService.getAirlineById(id);
        return ResponseEntity.ok(airline);
    }

    /**
     * Получает информацию об авиакомпании по её имени.
     * <p>
     * Метод обрабатывает GET-запрос для получения авиакомпании по её имени. Поиск нечувствителен к регистру.
     * </p>
     *
     * @param name имя авиакомпании
     * @return {@link ResponseEntity} с найденной авиакомпанией
     */

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Airline> getAirlineByName(@PathVariable("name") String name) {
        Airline airline = airlineService.getAirlineByNameIgnoreCase(name);
        return ResponseEntity.ok(airline);
    }

    /**
     * Получает список всех авиакомпаний.
     * <p>
     * Метод обрабатывает GET-запрос для получения списка всех авиакомпаний.
     * </p>
     *
     * @return {@link ResponseEntity} с списком всех авиакомпаний
     */
    @GetMapping("/all")
    public ResponseEntity<List<Airline>> getAllAirlines() {
        List<Airline> airline = airlineService.getAllAirlines();
        return ResponseEntity.ok(airline);
    }

    /**
     * Удаляет авиакомпанию по её идентификатору.
     * <p>
     * Метод обрабатывает DELETE-запрос для удаления авиакомпании по её идентификатору.
     * </p>
     *
     * @param id идентификатор авиакомпании
     * @return {@link ResponseEntity} без содержимого
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirline(@PathVariable("id") Integer id) {
        airlineService.deleteAirlineById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет информацию об авиакомпании по её идентификатору.
     * <p>
     * Метод обрабатывает PATCH-запрос для обновления информации об авиакомпании по её идентификатору.
     * </p>
     *
     * @param id   идентификатор авиакомпании
     * @param name новое имя авиакомпании
     * @return {@link ResponseEntity} с обновленной авиакомпанией
     */

    @PatchMapping("/{id}/{name}")
    public ResponseEntity<Airline> updateAirline(@PathVariable("id") Integer id,
                                                 @PathVariable("name") String name) {
        return ResponseEntity.ok(airlineService.updateAirline(id, name));
    }
}
