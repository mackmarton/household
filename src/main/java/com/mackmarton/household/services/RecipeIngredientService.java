package com.mackmarton.household.services;

import com.mackmarton.household.dto.RecipeIngredientDTO;
import com.mackmarton.household.entities.RecipeIngredient;
import com.mackmarton.household.mappers.RecipeIngredientMapper;
import com.mackmarton.household.repositories.RecipeIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeIngredientService {
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeIngredientMapper recipeIngredientMapper;

    @Transactional(readOnly = true)
    public List<RecipeIngredientDTO> getRecipeIngredientsByRecipeId(int recipeId) {
        return recipeIngredientMapper.toDtos(recipeIngredientRepository.getRecipeIngredientsByRecipeId(recipeId));
    }

    @Transactional
    public RecipeIngredientDTO createRecipeIngredient(RecipeIngredientDTO recipeIngredientDto) {
        RecipeIngredient recipeIngredient = recipeIngredientMapper.toEntity(recipeIngredientDto);
        return recipeIngredientMapper.toDto(recipeIngredientRepository.save(recipeIngredient));
    }

    @Transactional
    public void deleteByRecipeId(int recipeId) {
        recipeIngredientRepository.deleteByRecipeId(recipeId);
    }

    public void deleteByIngredientId(int id) {
        recipeIngredientRepository.deleteByIngredientId(id);
    }

    public boolean deleteRecipeIngredientById(int id) {
        return recipeIngredientRepository.findById(id).map(recipeIngredient -> {
            recipeIngredientRepository.delete(recipeIngredient);
            return true;
        }).orElse(false);
    }

    public Optional<RecipeIngredientDTO> updateRecipeIngredient(int id, RecipeIngredientDTO recipeIngredientDTO) {
        if (!recipeIngredientDTO.getId().equals(id)) {
            return Optional.empty();
        }

        return recipeIngredientRepository.findById(id).map(recipeIngredient -> {
            recipeIngredient.setIsAvailable(recipeIngredientDTO.getIsAvailable());
            return recipeIngredientMapper.toDto(recipeIngredientRepository.save(recipeIngredient));
        });
    }
}
