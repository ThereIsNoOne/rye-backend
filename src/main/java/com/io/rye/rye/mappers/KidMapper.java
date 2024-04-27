package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.KidDto;
import com.io.rye.rye.dto.KidRegisterForm;
import com.io.rye.rye.entity.Guardian;
import com.io.rye.rye.entity.Item;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.repository.GuardianRepository;
import com.io.rye.rye.repository.ItemRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class KidMapper {

    public static Kid fromDto(KidDto kidDto) {
        Kid kid = new Kid();

        kid.setId(kidDto.getId());
        kid.setPassword(kidDto.getPassword());
        kid.setUsername(kidDto.getUsername());
        kid.setBalance(kidDto.getBalance());

        return kid;
    }

    public static Kid fromDto(KidDto kidDto, ItemRepository itemRepository) {
        Kid kid = fromDto(kidDto);

        kid.setItems(
                StreamSupport
                        .stream(itemRepository.findAllById(kidDto.getItems()).spliterator(), false)
                        .collect(Collectors.toSet())
        );
        return kid;
    }

    public static Kid fromDto(KidDto kidDto, GuardianRepository guardianRepository) {
        Kid kid = fromDto(kidDto);

        kid.setGuardians(
                StreamSupport
                        .stream(guardianRepository.findAllById(kidDto.getGuardians()).spliterator(), false)
                        .collect(Collectors.toSet())
        );
        return kid;
    }

    public static Kid fromDto(KidDto kidDto, GuardianRepository guardianRepository, ItemRepository itemRepository) {
        Kid kid = fromDto(kidDto, itemRepository);

        kid.setGuardians(
                StreamSupport
                        .stream(guardianRepository.findAllById(kidDto.getGuardians()).spliterator(), false)
                        .collect(Collectors.toSet())
        );
        return kid;
    }

    public static KidDto toDto(Kid kid) {
        KidDto kidDto = new KidDto();

        kidDto.setBalance(kid.getBalance());
        kidDto.setId(kid.getId());
        kidDto.setPassword(kid.getPassword());
        kidDto.setUsername(kid.getUsername());

        kidDto.setGuardians(
                kid.getGuardians()
                        .stream()
                        .map(Guardian::getId)
                        .collect(Collectors.toSet())
        );
        kidDto.setItems(
                kid.getItems()
                        .stream()
                        .map(Item::getId)
                        .collect(Collectors.toSet())
        );

        return kidDto;
    }

    public static Kid fromRegisterForm(KidRegisterForm kidRegisterForm) {
        Kid kid = new Kid();
        kid.setUsername(kidRegisterForm.getUsername());
        kid.setPassword(kidRegisterForm.getPassword());
        kid.setGuardians(null);
        kid.setBalance(0);
        kid.setItems(null);
        return kid;
    }
}
