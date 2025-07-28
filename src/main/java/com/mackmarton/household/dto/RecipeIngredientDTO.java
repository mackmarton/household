package com.mackmarton.household.dto;

import lombok.Data;

@Data
public class RecipeIngredientDTO {
    private Integer id;

    private Integer recipeId;

    private Integer ingredientId;

    private Boolean isAvailable;
}