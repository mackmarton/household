package com.mackmarton.household.mappers;

import com.mackmarton.household.dto.ItemDTO;
import com.mackmarton.household.entities.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDto(Item item);

    Item toEntity(ItemDTO dto);

    List<ItemDTO> toDtos(List<Item> all);
}

