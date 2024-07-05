package com.example.timetrackingsystem.controller;

import com.example.timetrackingsystem.entity.Airline;
import com.example.timetrackingsystem.service.AirlineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AirlineControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AirlineService airlineService;

    @InjectMocks
    private AirlineController airlineController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(airlineController).build();
    }

    @Test
    void addAirline() throws Exception {
        Airline airline = new Airline();
        airline.setAirlineName("Test Airline");
        when(airlineService.addAirline(anyString())).thenReturn(airline);

        mockMvc.perform(post("/airline")
                        .param("name", "Test Airline")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlineName").value("Test Airline"));

        verify(airlineService, times(1)).addAirline(anyString());
    }

    @Test
    void getAirlineById() throws Exception {
        Airline airline = new Airline();
        airline.setAirlineName("Test Airline");
        when(airlineService.getAirlineById(anyInt())).thenReturn(airline);

        mockMvc.perform(get("/airline/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlineName").value("Test Airline"));

        verify(airlineService, times(1)).getAirlineById(anyInt());
    }

    @Test
    void getAirlineByName() throws Exception {
        Airline airline = new Airline();
        airline.setAirlineName("Test Airline");
        when(airlineService.getAirlineByNameIgnoreCase(anyString())).thenReturn(airline);

        mockMvc.perform(get("/airline/by-name/Test Airline")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlineName").value("Test Airline"));

        verify(airlineService, times(1)).getAirlineByNameIgnoreCase(anyString());
    }

    @Test
    void getAllAirlines() throws Exception {
        Airline airline1 = new Airline();
        airline1.setAirlineName("Airline 1");
        Airline airline2 = new Airline();
        airline2.setAirlineName("Airline 2");
        List<Airline> airlines = Arrays.asList(airline1, airline2);
        when(airlineService.getAllAirlines()).thenReturn(airlines);

        mockMvc.perform(get("/airline/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].airlineName").value("Airline 1"))
                .andExpect(jsonPath("$[1].airlineName").value("Airline 2"));

        verify(airlineService, times(1)).getAllAirlines();
    }

    @Test
    void updateAirline() throws Exception {
        Airline airline = new Airline();
        airline.setAirlineName("Updated Airline");
        when(airlineService.updateAirline(anyInt(), anyString())).thenReturn(airline);

        mockMvc.perform(patch("/airline/1/Updated Airline")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlineName").value("Updated Airline"));

        verify(airlineService, times(1)).updateAirline(anyInt(), anyString());
    }
}
