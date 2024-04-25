package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.GuardianDto;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.repository.GuardianRepository;
import com.io.rye.rye.repository.KidRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//TODO: After PoC stage refactor to hide passwords!
public class GuardianMapper {

    public static Guardian fromDto(GuardianDto guardianDto, KidRepository repository) {
        Guardian guardian = fromDto(guardianDto);

        guardian.setKids(
                StreamSupport
                        .stream(repository.findAllById(guardianDto.getKids()).spliterator(), false)
                        .collect(Collectors.toSet())
        );

        return guardian;
    }

    public static Guardian fromDto(GuardianDto guardianDto) {
        Guardian guardian = new Guardian();
        guardian.setEmail(guardianDto.getEmail());
        guardian.setId(guardianDto.getId());
        guardian.setPassword(guardianDto.getPassword());
        guardian.setUsername(guardianDto.getUsername());
        guardian.setFamilyMember(guardianDto.getFamilyMember());


        return guardian;
    }

    public static GuardianDto toDto(Guardian guardian) {
        GuardianDto guardianDto = new GuardianDto();
        guardianDto.setId(guardian.getId());
        guardianDto.setPassword(guardian.getPassword());
        guardianDto.setUsername(guardian.getUsername());
        guardianDto.setFamilyMember(guardian.getFamilyMember());
        guardianDto.setEmail(guardian.getEmail());

        guardianDto.setKids(guardian.getKids().stream()
                .map(Kid::getId)
                .collect(Collectors.toSet()));
        return guardianDto;
    }
}
