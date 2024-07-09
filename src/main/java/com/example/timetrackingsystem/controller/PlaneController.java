package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.entity.Plane;
import com.example.timetrackingsystem.service.PlaneService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для управления запросами, связанными с самолётами.
 * <p>
 * Этот контроллер предоставляет REST API для создания, получения, обновления и удаления самолётов,
 * а также для получения списка всех самолётов и самолётов по идентификатору авиакомпании.
 * </p>
 */
@RestController
@RequestMapping("/plane")
@Tag(name = "Самолет")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    /**
     * Создаёт новый самолёт.
     * <p>
     * Метод обрабатывает POST-запрос для создания нового самолёта с указанными параметрами:
     * имя, год выпуска и идентификатор авиакомпании.
     * </p>
     *
     * @param name      имя самолёта
     * @param year      год выпуска самолёта
     * @param airlineId идентификатор авиакомпании, к которой относится самолёт
     * @return {@link ResponseEntity} с созданным объектом {@link Plane}
     */

    @PostMapping()
    public ResponseEntity<Plane> addPlane(@RequestParam("name") String name,
                                          @RequestParam("year") Integer year,
                                          @RequestParam("airlineId") Integer airlineId) {
        Plane plane = planeService.addPlane(name, year, airlineId);
        return ResponseEntity.ok(plane);
    }

    /**
     * Получает информацию о самолёте по его идентификатору.
     * <p>
     * Метод обрабатывает GET-запрос для получения информации о самолёте по его уникальному идентификатору.
     * </p>
     *
     * @param id идентификатор самолёта
     * @return {@link ResponseEntity} с объектом {@link Plane}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Plane> getPlaneById(@PathVariable("id") Integer id) {
        Plane plane = planeService.getPlaneById(id);
        return ResponseEntity.ok(plane);
    }

    /**
     * Получает информацию о самолёте по его имени.
     * <p>
     * Метод обрабатывает GET-запрос для получения информации о самолёте по его имени.
     * Имя фильтруется без учета регистра.
     * </p>
     *
     * @param name имя самолёта
     * @return {@link ResponseEntity} с объектом {@link Plane}
     */
    @GetMapping("by-name/{name}")
    public ResponseEntity<Plane> getPlaneByName(@PathVariable("name") String name) {
        Plane plane = planeService.getPlaneByNameIgnoreCase(name);
        return ResponseEntity.ok(plane);
    }

    /**
     * Получает список всех самолётов.
     * <p>
     * Метод обрабатывает GET-запрос для получения списка всех самолётов в системе.
     * </p>
     *
     * @return {@link ResponseEntity} со списком объектов {@link Plane}
     */
    @GetMapping("/all")
    public ResponseEntity<List<Plane>> getAllPlanes() {
        List<Plane> planes = planeService.getAllPlanes();
        return ResponseEntity.ok(planes);
    }

    /**
     * Удаляет самолёт по его идентификатору.
     * <p>
     * Метод обрабатывает DELETE-запрос для удаления самолёта по его уникальному идентификатору.
     * </p>
     *
     * @param id идентификатор самолёта
     * @return {@link ResponseEntity} с пустым телом ответа
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlane(@PathVariable("id") Integer id) {
        planeService.deletePlaneById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет информацию о самолёте.
     * <p>
     * Метод обрабатывает PATCH-запрос для обновления информации о самолёте с указанными параметрами:
     * идентификатор, имя, год выпуска и идентификатор авиакомпании.
     * </p>
     *
     * @param id        идентификатор самолёта
     * @param name      новое имя самолёта
     * @param year      новый год выпуска самолёта
     * @param airlineId новый идентификатор авиакомпании
     * @return {@link ResponseEntity} с обновлённым объектом {@link Plane}
     */
    @PatchMapping()
    public ResponseEntity<Plane> updatePlane(@RequestParam("id") Integer id,
                                             @RequestParam("name") String name,
                                             @RequestParam("year") Integer year,
                                             @RequestParam("airlineId") Integer airlineId) {
        return ResponseEntity.ok(planeService.updatePlane(id, name, year, airlineId));
    }

    /**
     * Получает список самолётов по идентификатору авиакомпании.
     * <p>
     * Метод обрабатывает GET-запрос для получения списка самолётов, которые принадлежат указанной авиакомпании.
     * </p>
     *
     * @param airlineId идентификатор авиакомпании
     * @return {@link ResponseEntity} со списком объектов {@link Plane}
     */
    @GetMapping("by-airline/{airlineId}")
    public ResponseEntity<List<Plane>> getPlaneByAirlineId(@PathVariable("airlineId") Integer airlineId) {
        List<Plane> planes = planeService.getPlanesByAirlineId(airlineId);
        return ResponseEntity.ok(planes);
    }
}
