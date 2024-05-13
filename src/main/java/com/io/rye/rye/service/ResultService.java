package com.io.rye.rye.service;


import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.exception.InvalidInputException;
import com.io.rye.rye.mappers.ResultMapper;
import com.io.rye.rye.repository.KidRepository;
import com.io.rye.rye.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    private final KidRepository kidRepository;

    @Value("${jwt.token.key}")
    private String key;

    @Autowired
    public ResultService(ResultRepository resultRepository, KidRepository kidRepository) {
        this.resultRepository = resultRepository;
        this.kidRepository = kidRepository;
    }

    public Result addResult(ResultDto resultDto) throws InvalidInputException {
        Result result = ResultMapper.fromDto(resultDto, kidRepository);
        return resultRepository.save(result);
    }

    public Iterable<Result> getAllResults(String id) throws InvalidInputException {
        return resultRepository.findByKid(id);
    }
}