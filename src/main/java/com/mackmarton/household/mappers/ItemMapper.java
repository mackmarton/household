package com.mackmarton.household.mappers;

import com.mackmarton.household.dto.ItemDTO;
import com.mackmarton.household.entities.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDTO toDto(Item item);

    Item toEntity(ItemDTO dto);

}

