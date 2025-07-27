package com.mackmarton.household.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "recipeId")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredientId")
    private Item ingredient;

    private Boolean isAvailable;
}
