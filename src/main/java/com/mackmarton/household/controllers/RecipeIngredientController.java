package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.RecipeIngredientDTO;
import com.mackmarton.household.services.RecipeIngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/recipeingredients")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;

    @GetMapping("/{recipeId}")
    public ResponseEntity<List<RecipeIngredientDTO>> getAllIngredientsForRecipeId(@PathVariable int recipeId) {
        return ResponseEntity.ok(recipeIngredientService.getRecipeIngredientsByRecipeId(recipeId));
    }

    @PostMapping
    public ResponseEntity<RecipeIngredientDTO> createRecipeIngredient(@RequestBody RecipeIngredientDTO recipeIngredientDTO) {
        return ResponseEntity.ok(recipeIngredientService.createRecipeIngredient(recipeIngredientDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipeIngredient(@PathVariable int id) {
        boolean deleted = recipeIngredientService.deleteRecipeIngredientById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
