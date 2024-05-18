package com.io.rye.rye.service;

import com.io.rye.rye.entity.Result;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.stream.StreamSupport;

@Service
public class StatisticsService {

    private final ResultRepository resultRepository;

    @Autowired
    public StatisticsService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public long getGameCountByMode(String mode, String id) throws InvalidInputException {
        if (mode.equalsIgnoreCase("mimic-from-name")
                || mode.equalsIgnoreCase("mimic-from-picture")
                || mode.equalsIgnoreCase("recognize-from-picture")) {
            Iterable<Result> results = resultRepository.findByKidAndMode(id, mode);
            return StreamSupport.stream(results.spliterator(), false).count();
        }
        else {
            throw new InvalidInputException("Invalid game mode entered.");
        }
    }

    public long getGameCountByDate(String time, String id) throws InvalidInputException {
        if (time.equalsIgnoreCase("week")
                || time.equalsIgnoreCase("month")
                || time.equalsIgnoreCase("year")) {
            Iterable<Result> results = getResultsByPeriod(id, time);
            return StreamSupport.stream(results.spliterator(), false).count();
        }
        else {
            throw new InvalidInputException("Invalid time period entered.");
        }
    }

    public long getGameCountByModeAndDate(String mode, String time, String id) throws InvalidInputException {
        if (mode.equalsIgnoreCase("mimic-from-name")
                || mode.equalsIgnoreCase("mimic-from-picture")
                || mode.equalsIgnoreCase("recognize-from-picture")
                && (time.equalsIgnoreCase("week")
                || time.equalsIgnoreCase("month")
                || time.equalsIgnoreCase("year"))) {
            if (time.equalsIgnoreCase("week")) {
                Iterable<Result> results = getResultsByModeAndDateLastWeek(id, mode);
                return StreamSupport.stream(results.spliterator(), false).count();
            }
            else if (time.equalsIgnoreCase("month")) {
                Iterable<Result> results = getResultsByModeAndDateLastMonth(id, mode);
                return StreamSupport.stream(results.spliterator(), false).count();
            }
            else if (time.equalsIgnoreCase("year")) {
                Iterable<Result> results = getResultsByModeAndDateLastYear(id, mode);
                return StreamSupport.stream(results.spliterator(), false).count();
            }
            else {
                return -1;
            }
        }
        else {
            throw new InvalidInputException("Invalid game mode or time period entered.");
        }
    }

    public Iterable<Result> getResultsByMode(String mode, String id) throws InvalidInputException {
        if (mode.equalsIgnoreCase("mimic-from-name")
                || mode.equalsIgnoreCase("mimic-from-picture")
                || mode.equalsIgnoreCase("recognize-from-picture")) {
            return resultRepository.findByKidAndMode(id, mode);
        }
        else {
            throw new InvalidInputException("Invalid game mode entered.");
        }
    }

    public Iterable<Result> getResultsByDate(String time, String id) throws InvalidInputException {
        if (time.equalsIgnoreCase("week")
                || time.equalsIgnoreCase("month")
                || time.equalsIgnoreCase("year")) {
            return getResultsByPeriod(id, time);
        }
        else {
            throw new InvalidInputException("Invalid time period entered.");
        }
    }

    public Iterable<Result> getResultsByModeAndDate(String mode, String time, String id) throws InvalidInputException {
        if (mode.equalsIgnoreCase("mimic-from-name")
                || mode.equalsIgnoreCase("mimic-from-picture")
                || mode.equalsIgnoreCase("recognize-from-picture")
                && (time.equalsIgnoreCase("week")
                || time.equalsIgnoreCase("month")
                || time.equalsIgnoreCase("year"))) {
            if (time.equalsIgnoreCase("week")) {
                return getResultsByModeAndDateLastWeek(id, mode);
            }
            else if (time.equalsIgnoreCase("month")) {
                return getResultsByModeAndDateLastMonth(id, mode);
            }
            else if (time.equalsIgnoreCase("year")) {
                return getResultsByModeAndDateLastYear(id, mode);
            }
            else {
                return null;
            }
        }
        else {
            throw new InvalidInputException("Invalid game mode or time period entered.");
        }
    }

    private Iterable<Result> getResultsByPeriod(String id, String time) {
        Date today = new Date(System.currentTimeMillis());
        Date startDate = new Date(System.currentTimeMillis());
        if (time.equalsIgnoreCase("week")) {
            startDate = subtractDays(today, 7);
        }
        else if (time.equalsIgnoreCase("month")) {
            startDate = subtractDays(today, 30);
        }
        else if (time.equalsIgnoreCase("year")) {
            startDate = subtractDays(today, 365);
        }
        return resultRepository.findByKidAndDate(id, startDate);
    }

    private Date subtractDays(Date date, int days) {
        LocalDate localDate = date.toLocalDate();
        LocalDate newLocalDate = localDate.minusDays(days);
        return Date.valueOf(newLocalDate);
    }

    private Iterable<Result> getResultsByModeAndDateLastWeek(String kidId, String mode) {
        Date today = new Date(System.currentTimeMillis());
        Date oneWeekAgo = subtractDays(today, 7);
        return resultRepository.findByKidAndModeAndDate(kidId, mode, oneWeekAgo);
    }

    private Iterable<Result> getResultsByModeAndDateLastMonth(String kidId, String mode) {
        Date today = new Date(System.currentTimeMillis());
        Date oneMonthAgo = subtractDays(today, 30);
        return resultRepository.findByKidAndModeAndDate(kidId, mode, oneMonthAgo);
    }

    private Iterable<Result> getResultsByModeAndDateLastYear(String kidId, String mode) {
        Date today = new Date(System.currentTimeMillis());
        Date oneYearAgo = subtractDays(today, 365);
        return resultRepository.findByKidAndModeAndDate(kidId, mode, oneYearAgo);
    }
}
