package com.example.timetrackingsystem.service.impl;

import com.example.timetrackingsystem.entity.Airline;
import com.example.timetrackingsystem.exceptions.AirlineNotFoundException;
import com.example.timetrackingsystem.repository.AirlineRepository;
import com.example.timetrackingsystem.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация сервиса для управления сущностями {@link Airline}.
 * <p>
 * Этот сервис предоставляет методы для работы с авиакомпаниями, включая добавление, получение, обновление и удаление.
 * Все операции с данными выполняются в рамках транзакций.
 * </p>
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    /**
     * Добавляет новую авиакомпанию с указанным именем.
     *
     * @param name имя авиакомпании
     * @return {@link Airline}
     */
    @Override
    public Airline addAirline(String name) {
        return airlineRepository.save(new Airline(name));
    }

    /**
     * Получает авиакомпанию по её идентификатору.
     *
     * @param id идентификатор авиакомпании
     * @return {@link Airline}
     * @throws AirlineNotFoundException если авиакомпания с таким идентификатором не найдена
     */
    @Override
    public Airline getAirlineById(Integer id) {
        return airlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
    }

    /**
     * Получает все авиакомпании.
     *
     * @return {@link List<Airline>}
     */
    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    /**
     * Удаляет авиакомпанию по её идентификатору.
     *
     * @param id идентификатор авиакомпании
     */
    @Override
    public void deleteAirlineById(Integer id) {
        airlineRepository.deleteById(id);
    }

    /**
     * Обновляет имя авиакомпании по её идентификатору.
     *
     * @param id   идентификатор авиакомпании
     * @param name новое имя авиакомпании
     * @return обновлённая авиакомпания
     * @throws AirlineNotFoundException если авиакомпания с таким идентификатором не найдена
     */
    @Override
    public Airline updateAirline(Integer id, String name) {
        Airline airline = airlineRepository.findById(id).orElseThrow(AirlineNotFoundException::new);
        airline.setAirlineName(name);
        return airlineRepository.save(airline);
    }

    /**
     * Получает авиакомпанию по её имени, игнорируя регистр символов.
     *
     * @param name имя авиакомпании
     * @return авиакомпания с указанным именем
     * @throws AirlineNotFoundException если авиакомпания с таким именем не найдена
     */
    @Override
    public Airline getAirlineByNameIgnoreCase(String name) {
        return airlineRepository.findByAirlineNameIgnoreCase(name).orElseThrow(AirlineNotFoundException::new);
    }

}
