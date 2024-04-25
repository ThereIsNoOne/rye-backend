package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.ParentTaskDto;
import com.io.rye.rye.entity.ParentTask;
import com.io.rye.rye.repository.KidRepository;

public class ParentTaskMapper {

    public static ParentTask fromDto(ParentTaskDto parentTaskDto, KidRepository kidRepository) {
        ParentTask parentTask = new ParentTask();

        parentTask.setId(parentTaskDto.getId());
        parentTask.setDate(parentTaskDto.getDate());
        parentTask.setKid(kidRepository.findById(parentTaskDto.getKid()).orElse(null));
        parentTask.setDuration(parentTaskDto.getDuration());
        parentTask.setType(parentTaskDto.getType());
        parentTask.setLength(parentTaskDto.getLength());

        return parentTask;
    }

    public static ParentTaskDto toDto(ParentTask parentTask) {
        ParentTaskDto parentTaskDto = new ParentTaskDto();

        parentTaskDto.setId(parentTask.getId());
        parentTaskDto.setDate(parentTask.getDate());
        parentTaskDto.setDuration(parentTask.getDuration());
        parentTaskDto.setType(parentTask.getType());
        parentTaskDto.setLength(parentTask.getLength());
        parentTaskDto.setKid(parentTask.getKid().getId());

        return parentTaskDto;

    }
}
