package com.io.rye.rye.mappers;

import com.io.rye.rye.dto.ItemDto;
import com.io.rye.rye.entity.Item;
import com.io.rye.rye.entity.Kid;
import com.io.rye.rye.repository.KidRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ItemMapper {

    public static Item fromDto(ItemDto itemDto) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setPrice(itemDto.getPrice());

        return item;
    }

    public static Item fromDto(ItemDto itemDto, KidRepository repository) {
        Item item = fromDto(itemDto);

        item.setKid(
                StreamSupport.stream(repository.findAllById(itemDto.getKids()).spliterator(), false)
                        .collect(Collectors.toSet())
        );
        return item;
    }


    public static ItemDto toDto(Item item) {
        ItemDto itemDto = new ItemDto();

        itemDto.setId(item.getId());
        itemDto.setPrice(item.getPrice());
        itemDto.setKids(item.getKid().stream()
                .map(Kid::getId)
                .collect(Collectors.toSet()));
        return itemDto;
    }
}
