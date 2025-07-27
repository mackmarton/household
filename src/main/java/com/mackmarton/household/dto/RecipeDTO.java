package com.mackmarton.household.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class RecipeDTO {

    private Integer id;
    private String description;
    private List<RecipeIngredientDTO> ingredients;

}
