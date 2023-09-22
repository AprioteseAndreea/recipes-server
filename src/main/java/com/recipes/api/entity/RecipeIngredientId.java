package com.recipes.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;


import java.io.Serializable;

/**
 * It's a class that represents the composite primary key of the RecipeIngredient entity
 */
@Builder
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class RecipeIngredientId implements Serializable {
    @Column(name = "recipe_id")
    private Long recipeId;

    @Column(name = "ingredient_id")
    private Long ingredientId;
}
