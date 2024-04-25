package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.MascotDetailsDto;
import com.io.rye.rye.entity.MascotDetails;
import com.io.rye.rye.repository.KidRepository;

public class MascotDetailsMapper {

    public static MascotDetails fromDto(MascotDetailsDto mascotDetailsDto, KidRepository kidRepository) {
        MascotDetails mascotDetails = new MascotDetails();

        mascotDetails.setId(mascotDetailsDto.getId());
        mascotDetails.setFeetDetail(mascotDetailsDto.getFeetDetail());
        mascotDetails.setHeadDetail(mascotDetailsDto.getHeadDetail());
        mascotDetails.setNeckDetail(mascotDetailsDto.getNeckDetail());
        mascotDetails.setColor(mascotDetailsDto.getColor());

        mascotDetails.setKid(kidRepository.findById(mascotDetailsDto.getKid()).orElse(null));

        return mascotDetails;
    }

    public static MascotDetailsDto toDto(MascotDetails mascotDetails) {
        MascotDetailsDto mascotDetailsDto = new MascotDetailsDto();

        mascotDetailsDto.setId(mascotDetails.getId());
        mascotDetailsDto.setHeadDetail(mascotDetails.getHeadDetail());
        mascotDetailsDto.setNeckDetail(mascotDetails.getNeckDetail());
        mascotDetailsDto.setFeetDetail(mascotDetails.getFeetDetail());
        mascotDetailsDto.setColor(mascotDetails.getColor());

        mascotDetailsDto.setKid(mascotDetails.getKid().getId());

        return mascotDetailsDto;
    }
}
