package com.io.rye.rye.service;


import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.mappers.ResultMapper;
import com.io.rye.rye.repository.KidRepository;
import com.io.rye.rye.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ResultRepository resultRepository;

    private final KidRepository kidRepository;

    @Autowired
    public ResultService(ResultRepository resultRepository, KidRepository kidRepository) {
        this.resultRepository = resultRepository;
        this.kidRepository = kidRepository;
    }

    public Result addResult(ResultDto resultDto, String id) {
        Result result = ResultMapper.fromDto(resultDto, kidRepository, Integer.parseInt(id));
        return resultRepository.save(result);
    }

    public Iterable<Result> getAllResults(String id) {
        return resultRepository.findByKid(id);
    }
}