package com.mackmarton.household.mappers;

import com.mackmarton.household.dto.RecipeDTO;
import com.mackmarton.household.dto.RecipeIngredientDTO;
import com.mackmarton.household.entities.Recipe;
import com.mackmarton.household.entities.RecipeIngredient;
import com.mackmarton.household.services.RecipeIngredientService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public abstract class RecipeMapper {

    private RecipeIngredientService recipeIngredientService;
    protected ItemMapper itemMapper;

    @Autowired
    public void setRecipeIngredientsService(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }

    @Autowired
    public void setItemMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public RecipeDTO toDto(Recipe recipe) {
        RecipeDTO dto = new RecipeDTO();
        dto.setId(recipe.getId());
        dto.setDescription(recipe.getDescription());
        dto.setIngredients(mapIngredients(recipe));
        return dto;
    }

    public abstract Recipe toEntity(RecipeDTO recipeDTO);

    public abstract List<RecipeDTO> toDtos(List<Recipe> recipe);

    protected List<RecipeIngredientDTO> mapIngredients(Recipe recipe) {
        List<RecipeIngredient> recipeIngredients = recipeIngredientService.getRecipeIngredientsByRecipeId(recipe.getId());
        List<RecipeIngredientDTO> ingredients = new ArrayList<>();

        for (var recipeIngredient : recipeIngredients) {
            RecipeIngredientDTO dto = new RecipeIngredientDTO();
            dto.setIngredient(itemMapper.toDto(recipeIngredient.getIngredient()));
            dto.setIsAvailable(recipeIngredient.getIsAvailable());
            ingredients.add(dto);
        }

        return ingredients;
    }
}

