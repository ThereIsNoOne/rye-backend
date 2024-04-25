package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.ResultDto;
import com.io.rye.rye.entity.Result;
import com.io.rye.rye.repository.KidRepository;

public class ResultMapper {

    public static Result fromDto(ResultDto resultDto, KidRepository kidRepository) {
        Result result = new Result();

        result.setId(resultDto.getId());
        result.setResult(resultDto.getResult());
        result.setDate(resultDto.getDate());
        result.setMode(resultDto.getMode());
        result.setReward(resultDto.getReward());
        result.setTime(resultDto.getTime());
        result.setKid(kidRepository.findById(resultDto.getKid()).orElse(null));

        return result;
    }

    public static ResultDto toDto(Result result) {
        ResultDto resultDto = new ResultDto();

        resultDto.setId(result.getId());
        resultDto.setResult(result.getResult());
        resultDto.setDate(result.getDate());
        resultDto.setMode(result.getMode());
        resultDto.setReward(result.getReward());
        resultDto.setTime(result.getTime());
        resultDto.setKid(result.getKid().getId());

        return resultDto;
    }
}
