package com.mackmarton.household.dto;

import lombok.Data;

@Data
public class RecipeIngredientDTO {
    private ItemDTO ingredient;
    private Boolean isAvailable;
}