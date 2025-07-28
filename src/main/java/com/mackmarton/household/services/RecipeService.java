package com.mackmarton.household.services;

import com.mackmarton.household.dto.RecipeDTO;
import com.mackmarton.household.entities.Recipe;
import com.mackmarton.household.mappers.RecipeMapper;
import com.mackmarton.household.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeMapper recipeMapper;
    private final RecipeRepository recipeRepository;
    private final RecipeIngredientService recipeIngredientService;
    private final ItemService itemService;

    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        Recipe savedRecipe = recipeRepository.save(recipeMapper.toEntity(recipeDTO));
        return recipeMapper.toDto(savedRecipe);
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeMapper.toDtos(recipeRepository.findAll());
    }

    public Optional<RecipeDTO> getRecipeById(Integer id) {
        var optionalRecipe = recipeRepository.findById(id);
        return optionalRecipe.map(recipeMapper::toDto);
    }

    public Optional<RecipeDTO> updateRecipe(Integer id, RecipeDTO recipeDetails) {
        if (!id.equals(recipeDetails.getId())) {
            return Optional.empty();
        }

        return recipeRepository.findById(id).map(recipe -> {
            recipe.setDescription(recipeDetails.getDescription());
            recipe.setName(recipeDetails.getName());
            return recipeMapper.toDto(recipeRepository.save(recipe));
        });
    }

    public boolean deleteRecipe(Integer id) {
        recipeIngredientService.deleteByRecipeId(id);
        return recipeRepository.findById(id).map(recipe -> {
            recipeRepository.delete(recipe);
            return true;
        }).orElse(false);
    }
}
