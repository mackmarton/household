package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.RecipeDTO;
import com.mackmarton.household.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
@Tag(name = "Recipe", description = "Recipe management API")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    @Operation(summary = "Get all recipes", description = "Retrieves all recipes from the database")
    @ApiResponse(responseCode = "200", description = "Recipes retrieved successfully")
    public List<RecipeDTO> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recipe by ID", description = "Retrieves a specific recipe by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe found"),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content)
    })
    public ResponseEntity<RecipeDTO> getRecipeById(@Parameter(description = "Recipe ID") @PathVariable Integer id) {
        return recipeService.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create recipe", description = "Creates a new recipe with provided details")
    @ApiResponse(responseCode = "200", description = "Recipe created successfully")
    public RecipeDTO createRecipe(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Recipe details with ingredients")
            @RequestBody RecipeDTO recipe) {
        return recipeService.createRecipe(recipe);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update recipe", description = "Updates an existing recipe with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - ID mismatch", content = @Content),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content)
    })
    public ResponseEntity<RecipeDTO> updateRecipe(
            @Parameter(description = "Recipe ID") @PathVariable Integer id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated recipe details")
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
    @Operation(summary = "Delete recipe", description = "Deletes a recipe by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recipe deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Recipe not found", content = @Content)
    })
    public ResponseEntity<Void> deleteRecipe(@Parameter(description = "Recipe ID") @PathVariable Integer id) {
        boolean deleted = recipeService.deleteRecipe(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}