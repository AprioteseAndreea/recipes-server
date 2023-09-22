package com.recipes.api.dtos;

import com.recipes.api.entity.RecipeIngredientEntity;
import com.recipes.api.entity.enums.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.PositiveOrZero;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientDto {
    private Long id;
    private RecipeDto recipe;
    private IngredientDto ingredientDto;
    @PositiveOrZero
    private Double quantity;
    private UnitOfMeasure unitOfMeasure;


    public static RecipeIngredientDto fromEntity(final RecipeIngredientEntity recipeIngredientEntity){
        return null;
    }
}
