package com.recipes.api.dtos;

import com.recipes.api.entity.RecipeIngredientEntity;
import com.recipes.api.entity.RecipeInstructionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeInstructionDto {
    private String details;
    private Integer step;

    public static RecipeInstructionDto fromEntity(final RecipeInstructionEntity recipeInstructionEntity){
        return RecipeInstructionDto.builder()
                .step(recipeInstructionEntity.getStep())
                .details(recipeInstructionEntity.getDetails())
                .build();
    }
}
