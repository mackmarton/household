package com.mackmarton.household.mappers;

import com.mackmarton.household.dto.DishwashingDTO;
import com.mackmarton.household.entities.Dishwashing;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DishwashingMapper {

    public abstract DishwashingDTO toDto(Dishwashing dishwashing);

    public abstract List<DishwashingDTO> toDtos(List<Dishwashing> dishwashingEntities);
}
