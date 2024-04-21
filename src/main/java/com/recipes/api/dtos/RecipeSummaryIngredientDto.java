package com.recipes.api.dtos;

import com.recipes.api.entity.RecipeIngredientEntity;
import com.recipes.api.entity.enums.UnitOfMeasure;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeSummaryIngredientDto {
    private Integer ingredientId;
    private Double quantity;
    private UnitOfMeasure unitOfMeasure;

    public static RecipeSummaryIngredientDto fromEntity(final RecipeIngredientEntity recipeIngredientEntity){
        return RecipeSummaryIngredientDto.builder()
                .ingredientId(recipeIngredientEntity.getIngredient().getId())
                .quantity(recipeIngredientEntity.getQuantity())
                .unitOfMeasure(recipeIngredientEntity.getUnitOfMeasure())
                .build();
    }
}
