package com.mackmarton.household.repositories;

import com.mackmarton.household.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> { }
