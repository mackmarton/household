package com.mackmarton.household.mappers;

import com.mackmarton.household.dto.RecipeIngredientDTO;
import com.mackmarton.household.entities.Item;
import com.mackmarton.household.entities.Recipe;
import com.mackmarton.household.entities.RecipeIngredient;
import com.mackmarton.household.repositories.ItemRepository;
import com.mackmarton.household.repositories.RecipeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class RecipeIngredientMapper {
    private ItemRepository itemRepository;
    private RecipeRepository recipeRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setRecipeRepository(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Mapping(source = "recipeId", target = "recipe", qualifiedByName = "mapRecipeIdToRecipe")
    @Mapping(source = "ingredientId", target = "ingredient", qualifiedByName = "mapIngredientIdToIngredient")
    public abstract RecipeIngredient toEntity(RecipeIngredientDTO recipeIngredientDTO);

    @Mapping(source = "recipe.id", target = "recipeId")
    @Mapping(source = "ingredient.id", target = "ingredientId")
    public abstract RecipeIngredientDTO toDto(RecipeIngredient recipeIngredient);

    public abstract List<RecipeIngredientDTO> toDtos(List<RecipeIngredient> recipeIngredients);

    @Named("mapRecipeIdToRecipe")
    public Recipe mapRecipeIdToRecipe(int recipeId){
        return recipeRepository.findById(recipeId).orElseThrow();
    }

    @Named("mapIngredientIdToIngredient")
    public Item mapIngredientIdToIngredient(int ingredientId){
        return itemRepository.findById(ingredientId).orElseThrow();
    }
}
