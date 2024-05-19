package com.io.rye.rye.controller;

import com.io.rye.rye.TokenUtils;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/stats")
public class StatisticsController {
    private final StatisticsService statisticsService;

    private final TokenUtils tokenUtils;

    @Autowired
    public StatisticsController(StatisticsService statisticsService, TokenUtils tokenUtils) {
        this.statisticsService = statisticsService;
        this.tokenUtils = tokenUtils;
    }

    @GetMapping("/getGameCountByMode")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> getGameCountByMode(@RequestHeader HttpHeaders headers,
                                                                   @RequestParam("mode") String mode) throws ResponseStatusException {
        try {
            long gameCount = statisticsService.getGameCountByMode(mode, tokenUtils.getIdFromToken(headers));
            return new ResponseEntity<>(String.valueOf(gameCount), HttpStatus.OK);

        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getGameCountByDate")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> getGameCountByDate(@RequestHeader HttpHeaders headers,
                                                                          @RequestParam("time") String time) throws ResponseStatusException {
        try {
            long gameCount = statisticsService.getGameCountByDate(time, tokenUtils.getIdFromToken(headers));
            return new ResponseEntity<>(String.valueOf(gameCount), HttpStatus.OK);
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getGameCountByModeAndDate")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody ResponseEntity<String> getGameCountByModeAndDate(@RequestHeader HttpHeaders headers,
                                                           @RequestParam("mode") String mode,
                                                           @RequestParam("time") String time) throws ResponseStatusException {
        try {
            long gameCount = statisticsService.getGameCountByModeAndDate(mode, time, tokenUtils.getIdFromToken(headers));
            if (gameCount == -1) {
                return new ResponseEntity<>("Invalid request", HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<>(String.valueOf(gameCount), HttpStatus.OK);
            }
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getResultsByMode")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<Result> getResultsByMode(@RequestHeader HttpHeaders headers,
                                                           @RequestParam("mode") String mode) throws ResponseStatusException {
        try {
            return statisticsService.getResultsByMode(mode, tokenUtils.getIdFromToken(headers));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getResultsByDate")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<Result> getResultsByDate(@RequestHeader HttpHeaders headers,
                                                           @RequestParam("time") String time) throws ResponseStatusException {
        try {
            return statisticsService.getResultsByDate(time, tokenUtils.getIdFromToken(headers));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getResultsByModeAndDate")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<Result> getResultsByModeAndDate(@RequestHeader HttpHeaders headers,
                                                     @RequestParam("mode") String mode,
                                                     @RequestParam("time") String time) throws ResponseStatusException {
        try {
            return statisticsService.getResultsByModeAndDate(mode, time, tokenUtils.getIdFromToken(headers));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}
