package com.io.rye.rye.service;

import com.io.rye.rye.entity.Result;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.repository.ResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class StatisticsServiceTests {

    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetGameCountByMode_ValidMode() throws InvalidInputException {
        // Mock repository response
        when(resultRepository.findByKidAndMode(anyString(), anyString()))
                .thenReturn(Arrays.asList(new Result(), new Result()));

        long count = statisticsService.getGameCountByMode("mimic-from-name", "123");

        assertEquals(2, count);
    }

    @Test
    void testGetGameCountByMode_InvalidMode() {
        try {
            statisticsService.getGameCountByMode("invalid-mode", "123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid game mode entered.", e.getMessage());
        }
    }

    @Test
    void testGetGameCountByDate_ValidDate() throws InvalidInputException {
        // Mock repository response

        when(resultRepository.findByKidAndDate(anyString(), any(Date.class)))
                .thenReturn(Arrays.asList(new Result(), new Result()));

        long count = statisticsService.getGameCountByDate("week", "123");

        assertEquals(2, count);
    }


    @Test
    void testGetGameCountByDate_InvalidDate() {
        try {
            statisticsService.getGameCountByDate("invalid-period", "123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid time period entered.", e.getMessage());
        }
    }

    @Test
    void testGetGameCountByModeAndDate_ValidParameters() throws InvalidInputException {
        // Mock repository response

        when(resultRepository.findByKidAndModeAndDate(anyString(), anyString(), any(Date.class)))
                .thenReturn(Arrays.asList(new Result(), new Result()));

        long count = statisticsService.getGameCountByModeAndDate("mimic-from-name","week", "123");

        assertEquals(2, count);
    }

    @Test
    void testGetGameCountByModeAndDate_InvalidParameters() {
        try {
            statisticsService.getGameCountByModeAndDate("mimic_from_name", "invalid-period", "123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid game mode or time period entered.", e.getMessage());
        }
    }

    @Test
    void testGetResultsByMode_ValidMode() throws InvalidInputException {
        // Mock repository response
        when(resultRepository.findByKidAndMode(anyString(), anyString()))
                .thenReturn(Arrays.asList(new Result(), new Result()));

        Iterable<Result> results = statisticsService.getResultsByMode("mimic-from-name", "123");

        assertEquals(2, ((List<Result>) results).size());
    }

    @Test
    void testGetResultsByMode_InvalidMode(){
        try {
            statisticsService.getResultsByMode( "invalid-mode", "123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid game mode entered.", e.getMessage());
        }
    }

    @Test
    void testGetResultsByDate_ValidDate() throws InvalidInputException {
        // Mock repository response
        when(resultRepository.findByKidAndDate(anyString(), any(Date.class)))
                .thenReturn(Arrays.asList(new Result(), new Result()));

        Iterable<Result> results = statisticsService.getResultsByDate("week", "123");

        assertEquals(2, ((List<Result>) results).size());
    }

    @Test
    void testGetResultsByDate_InvalidDate(){
        try {
            statisticsService.getResultsByDate( "invalid-period", "123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid time period entered.", e.getMessage());
        }
    }

    @Test
    void testGetResultsByModeAndDate_ValidParameters() throws InvalidInputException {
        // Mock repository response
        when(resultRepository.findByKidAndModeAndDate(anyString(), anyString(), any(Date.class)))
                .thenReturn(Arrays.asList(new Result(), new Result()));

        Iterable<Result> results = statisticsService.getResultsByModeAndDate("mimic-from-name","week", "123");

        assertEquals(2, ((List<Result>) results).size());
    }

    @Test
    void testGetResultsByModeAndDate_InvalidParameters(){
        try {
            statisticsService.getResultsByModeAndDate( "invalid-mode","invalid-period", "123");
        } catch (InvalidInputException e) {
            assertEquals("Invalid game mode or time period entered.", e.getMessage());
        }
    }

}

