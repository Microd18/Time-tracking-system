package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.entity.Airline;
import com.example.timetrackingsystem.entity.Plane;
import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.exceptions.PlaneNotFoundException;
import com.example.timetrackingsystem.repository.AirlineRepository;
import com.example.timetrackingsystem.repository.PlaneRepository;
import com.example.timetrackingsystem.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса для работы с самолетами.
 * <p>
 * Этот сервис управляет бизнес-логикой, связанной с самолетами, включая добавление, обновление, удаление
 * и извлечение информации о самолетах.
 * </p>
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;
    private final AirlineRepository airlineRepository;

    /**
     * Добавляет новый самолет в базу данных.
     * <p>
     * Сначала проверяет существование авиакомпании по указанному идентификатору. Если авиакомпания не найдена,
     * выбрасывается {@link AirlineNotFoundException}. После этого сохраняет новый самолет в базе данных.
     * </p>
     *
     * @param name      имя самолета.
     * @param year      год выпуска самолета.
     * @param airlineId идентификатор авиакомпании, к которой принадлежит самолет.
     * @return добавленный {@link Plane}.
     * @throws AirlineNotFoundException если авиакомпания с указанным идентификатором не найдена.
     */
    @Override
    public Plane addPlane(String name, Integer year, Integer airlineId) {
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(AirlineNotFoundException::new);
        return planeRepository.save(new Plane(name, year, airline));
    }

    /**
     * Находит самолет по идентификатору.
     * <p>
     * Если самолет с указанным идентификатором не найден, выбрасывается {@link PlaneNotFoundException}.
     * </p>
     *
     * @param id идентификатор самолета.
     * @return {@link Plane} с указанным идентификатором.
     * @throws PlaneNotFoundException если самолет с указанным идентификатором не найден.
     */
    @Override
    public Plane getPlaneById(Integer id) {
        return planeRepository.findById(id).orElseThrow(PlaneNotFoundException::new);
    }

    /**
     * Получает список всех самолетов.
     * <p>
     * Возвращает все самолеты, хранящиеся в базе данных.
     * </p>
     *
     * @return список {@link Plane}.
     */
    @Override
    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    /**
     * Удаляет самолет по идентификатору.
     *
     * @param id идентификатор самолета для удаления.
     */
    @Override
    public void deletePlaneById(Integer id) {
        planeRepository.deleteById(id);
    }

    /**
     * Обновляет информацию о существующем самолете.
     * <p>
     * Сначала проверяет существование самолета и авиакомпании по указанным идентификаторам. Если любой из них не найден,
     * выбрасывается соответствующее исключение. После этого обновляет данные самолета и сохраняет их в базе данных.
     * </p>
     *
     * @param id        идентификатор самолета для обновления.
     * @param name      новое имя самолета.
     * @param year      новый год выпуска самолета.
     * @param airlineId новый идентификатор авиакомпании, к которой принадлежит самолет.
     * @return обновленный {@link Plane}.
     * @throws PlaneNotFoundException   если самолет с указанным идентификатором не найден.
     * @throws AirlineNotFoundException если авиакомпания с указанным идентификатором не найдена.
     */
    @Override
    public Plane updatePlane(Integer id, String name, Integer year, Integer airlineId) {
        Plane plane = planeRepository.findById(id).orElseThrow(PlaneNotFoundException::new);
        Airline airline = airlineRepository.findById(airlineId).orElseThrow(AirlineNotFoundException::new);
        plane.setPlaneName(name);
        plane.setYearOfManufacture(year);
        plane.setAirline(airline);
        return planeRepository.save(plane);
    }

    /**
     * Находит самолет по имени, игнорируя регистр.
     * <p>
     * Если самолет с указанным именем не найден, выбрасывается {@link PlaneNotFoundException}.
     * </p>
     *
     * @param name имя самолета.
     * @return {@link Plane} с указанным именем.
     * @throws PlaneNotFoundException если самолет с указанным именем не найден.
     */
    @Override
    public Plane getPlaneByNameIgnoreCase(String name) {
        return planeRepository.findByPlaneNameIgnoreCase(name).orElseThrow(PlaneNotFoundException::new);
    }

    /**
     * Получает список самолетов по идентификатору авиакомпании.
     * <p>
     * Возвращает все самолеты, которые принадлежат указанной авиакомпании.
     * </p>
     *
     * @param airlineId идентификатор авиакомпании.
     * @return список {@link Plane} для указанной авиакомпании.
     */
    @Override
    public List<Plane> getPlanesByAirlineId(Integer airlineId) {
        return planeRepository.findPlanesByAirline_Id(airlineId);
    }
}
