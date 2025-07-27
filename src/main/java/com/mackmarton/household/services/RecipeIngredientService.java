package com.mackmarton.household.services;

import com.mackmarton.household.entities.RecipeIngredient;
import com.mackmarton.household.repositories.RecipeIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Transactional(readOnly = true)
    public List<RecipeIngredient> getRecipeIngredientsByRecipeId(int recipeId){
        return recipeIngredientRepository.getRecipeIngredientsByRecipeId(recipeId);
    }

    @Transactional
    public RecipeIngredient createRecipeIngredient(RecipeIngredient  recipeIngredient){
        return recipeIngredientRepository.save(recipeIngredient);
    }

    @Transactional
    public void deleteByRecipeId(int recipeId) {
        recipeIngredientRepository.deleteByRecipeId(recipeId);
    }

    public void deleteByIngredientId(int id) {
        recipeIngredientRepository.deleteByIngredientId(id);
    }
}
