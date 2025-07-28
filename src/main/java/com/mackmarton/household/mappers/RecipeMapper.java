package com.mackmarton.household.mappers;

import com.mackmarton.household.dto.RecipeDTO;
import com.mackmarton.household.entities.Recipe;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public abstract class RecipeMapper {

    protected ItemMapper itemMapper;

    @Autowired
    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public RecipeDTO toDto(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setName(recipe.getName());
        dto.setDescription(recipe.getDescription());
        return dto;
    }

    public abstract Recipe toEntity(RecipeDTO recipeDTO);

    public abstract List<RecipeDTO> toDtos(List<Recipe> recipe);

}

