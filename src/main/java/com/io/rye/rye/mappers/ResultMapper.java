package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.repository.KidRepository;

import java.sql.Date;
import java.time.LocalDate;

public class ResultMapper {

    public static Result fromDto(ResultDto resultDto, KidRepository kidRepository, int id) {
        Result result = new Result();

        result.setResult(resultDto.getResult());
        result.setDate(Date.valueOf(LocalDate.now()));
        result.setMode(resultDto.getMode());
        result.setReward(resultDto.getReward());
        result.setTime(resultDto.getTime());
        result.setKid(kidRepository.findById(id).orElse(null));

        return result;
    }

    public static ResultDto toDto(Result result) {
        ResultDto resultDto = new ResultDto();

        resultDto.setResult(result.getResult());
        resultDto.setMode(result.getMode());
        resultDto.setReward(result.getReward());
        resultDto.setTime(result.getTime());

        return resultDto;
    }
}
