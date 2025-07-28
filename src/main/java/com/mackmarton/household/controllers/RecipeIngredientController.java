package com.mackmarton.household.controllers;

import com.mackmarton.household.dto.RecipeIngredientDTO;
import com.mackmarton.household.services.RecipeIngredientService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/recipeingredients")
@Tag(name = "Recipe Ingredient", description = "Recipe ingredient management API")
public class RecipeIngredientController {

    private final RecipeIngredientService recipeIngredientService;

    @GetMapping("/{recipeId}")
    @Operation(summary = "Get ingredients by recipe ID", description = "Retrieves all ingredients for a specific recipe")
    @ApiResponse(responseCode = "200", description = "Recipe ingredients retrieved successfully")
    public ResponseEntity<List<RecipeIngredientDTO>> getAllIngredientsForRecipeId(
            @Parameter(description = "Recipe ID") @PathVariable int recipeId) {
        return ResponseEntity.ok(recipeIngredientService.getRecipeIngredientsByRecipeId(recipeId));
    }

    @PostMapping
    @Operation(summary = "Create recipe ingredient", description = "Creates a new recipe ingredient")
    @ApiResponse(responseCode = "200", description = "Recipe ingredient created successfully")
    public ResponseEntity<RecipeIngredientDTO> createRecipeIngredient(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Recipe ingredient details")
            @RequestBody RecipeIngredientDTO recipeIngredientDTO) {
        return ResponseEntity.ok(recipeIngredientService.createRecipeIngredient(recipeIngredientDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update recipe ingredient", description = "Updates an existing recipe ingredient with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe ingredient updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - ID mismatch", content = @Content),
            @ApiResponse(responseCode = "404", description = "Recipe ingredient not found", content = @Content)
    })
    public ResponseEntity<RecipeIngredientDTO> updateRecipeIngredient(
            @Parameter(description = "Recipe ingredient ID") @PathVariable int id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated recipe ingredient details")
            @RequestBody RecipeIngredientDTO recipeIngredientDTO) {
        var updatedRecipeIngredient = recipeIngredientService.updateRecipeIngredient(id, recipeIngredientDTO);

        if(updatedRecipeIngredient.isEmpty() && !recipeIngredientDTO.getId().equals(id)){
            return ResponseEntity.badRequest().build();
        }

        return updatedRecipeIngredient
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete recipe ingredient", description = "Deletes a recipe ingredient by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Recipe ingredient deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Recipe ingredient not found", content = @Content)
    })
    public ResponseEntity<Void> deleteRecipeIngredient(
            @Parameter(description = "Recipe ingredient ID") @PathVariable int id) {
        boolean deleted = recipeIngredientService.deleteRecipeIngredientById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}