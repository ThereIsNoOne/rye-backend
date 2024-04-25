package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.PictureDto;
import com.io.rye.rye.entity.Picture;

public class PictureMapper {

    public static Picture fromDto(PictureDto pictureDto) {
        Picture picture = new Picture();

        picture.setId(pictureDto.getId());
        picture.setUrl(pictureDto.getUrl());
        picture.setEmotion(pictureDto.getEmotion());

        return picture;
    }

    public static PictureDto toDto(Picture picture) {
        PictureDto pictureDto = new PictureDto();

        pictureDto.setId(picture.getId());
        pictureDto.setUrl(picture.getUrl());
        pictureDto.setEmotion(picture.getEmotion());

        return pictureDto;
    }

}
