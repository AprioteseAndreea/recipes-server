package com.recipes.api.dtos;

import com.recipes.api.entity.RecipeEntity;
import com.recipes.api.entity.enums.CookingLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileRecipeDto {
    private Integer recipeId;
    private Integer prepTime;
    private Integer kcals;

    private CookingLevel cookingLevel;
    private String cuisineName;

    private List<DietDto> recipeDiets;
    private List<Integer> ingredientDtoList;

    public static UserProfileRecipeDto fromRecipeDto(RecipeEntity recipeEntity) {
        return UserProfileRecipeDto.builder()
                .recipeId(recipeEntity.getId())
                .prepTime(recipeEntity.getPrepTime())
                .kcals(recipeEntity.getKcals())
                .cookingLevel(recipeEntity.getCookingLevel())
                .cuisineName(String.valueOf(recipeEntity.getCuisine()))
                .recipeDiets(recipeEntity.getRecipeDiets().stream().map(DietDto::fromEntity).toList())
                .ingredientDtoList(recipeEntity
                        .getRecipeIngredientEntityList()
                        .stream()
                        .map(recipeIngredientDto -> recipeIngredientDto.getIngredient().getId())
                        .toList())
                .build();
    }
}
