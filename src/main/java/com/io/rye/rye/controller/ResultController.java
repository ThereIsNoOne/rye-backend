package com.io.rye.rye.controller;


import com.io.rye.rye.common.TokenUtils;
import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/result")
public class ResultController {
    private final ResultService resultService;

    private final TokenUtils tokenUtils;

    @Autowired
    public ResultController(ResultService resultService, TokenUtils tokenUtils) {
        this.resultService = resultService;
        this.tokenUtils = tokenUtils;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody Result addResult(@RequestHeader HttpHeaders headers, @RequestBody ResultDto resultDto) throws ResponseStatusException {
        try {
            return resultService.addResult(resultDto, tokenUtils.getIdFromToken(headers));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/getAll")
    @ResponseStatus(code = HttpStatus.OK)
    public @ResponseBody Iterable<Result> getAllResults(@RequestHeader HttpHeaders headers) throws ResponseStatusException {
        try {
            return resultService.getAllResults(tokenUtils.getIdFromToken(headers));
        } catch (InvalidInputException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }
}