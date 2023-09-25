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
public class RecipeDto {
    private Integer id;

    @NotBlank(message = "Ingredient name is mandatory")
    @Size(max = 100, message = "The maximum length is 100 characters")
    private String name;

    @PositiveOrZero
    private Integer prepTime;

    private CookingLevel cookingLevel;

    private String instructions;

    private String pictureUrl;

    @PositiveOrZero
    private Integer kcals;

    @PositiveOrZero
    private Integer favourites;

    private String cuisineName;

    private List<RecipeIngredientDto> ingredientDtoList;

    public static RecipeDto fromEntity(final RecipeEntity recipeEntity) {
        return RecipeDto.builder()
                .id(recipeEntity.getId())
                .name(recipeEntity.getName())
                .prepTime(recipeEntity.getPrepTime())
                .cookingLevel(recipeEntity.getCookingLevel())
                .instructions(recipeEntity.getInstructions())
                .pictureUrl(recipeEntity.getPictureUrl())
                .kcals(recipeEntity.getKcals())
                .favourites(recipeEntity.getFavourites())
                .cuisineName(recipeEntity.getCuisine().getName())
                .ingredientDtoList(
                        recipeEntity.getRecipeIngredientEntityList()
                                .stream()
                                .map(RecipeIngredientDto::fromEntity)
                                .toList())
                .build();
    }
}
