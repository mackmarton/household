package com.mackmarton.household.repositories;

import com.mackmarton.household.entities.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {
    List<RecipeIngredient> getRecipeIngredientsByRecipeId(int recipeId);

    void deleteByRecipeId(int recipeId);

    void deleteByIngredientId(int ingredientId);
}
