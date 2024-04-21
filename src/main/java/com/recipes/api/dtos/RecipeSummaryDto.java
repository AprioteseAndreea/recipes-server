package com.recipes.api.dtos;

import com.recipes.api.entity.RecipeEntity;
import com.recipes.api.entity.enums.CookingLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeSummaryDto {
    private Integer id;

    @PositiveOrZero
    private Integer prepTime;

    private CookingLevel cookingLevel;

    @PositiveOrZero
    private Integer kcals;

    private String cuisineName;

    private List<RecipeSummaryIngredientDto> ingredientDtoList;
    private List<DietDto> recipeDiets;
    public static RecipeSummaryDto fromEntity(final RecipeEntity recipeEntity) {
        return RecipeSummaryDto.builder()
                .id(recipeEntity.getId())
                .prepTime(recipeEntity.getPrepTime())
                .cookingLevel(recipeEntity.getCookingLevel())
                .kcals(recipeEntity.getKcals())
                .cuisineName(recipeEntity.getCuisine().getName())
                .ingredientDtoList(
                        recipeEntity.getRecipeIngredientEntityList()
                                .stream()
                                .map(RecipeSummaryIngredientDto::fromEntity)
                                .toList())
                .recipeDiets(
                        recipeEntity.getRecipeDiets()
                                .stream()
                                .map(DietDto::fromEntity)
                                .toList())
                .build();
    }
}
