package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.RecipeDTO;
import com.mackmarton.household.services.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> getRecipeById(@PathVariable Integer id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public RecipeDTO createRecipe(@RequestBody RecipeDTO recipe) {
        return recipeService.createRecipe(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> updateRecipe(
            @PathVariable Integer id,
            @RequestBody RecipeDTO recipeDetails) {
        Optional<RecipeDTO> updatedRecipe = recipeService.updateRecipe(id, recipeDetails);

        if (!id.equals(recipeDetails.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return updatedRecipe
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        boolean deleted = recipeService.deleteRecipe(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
