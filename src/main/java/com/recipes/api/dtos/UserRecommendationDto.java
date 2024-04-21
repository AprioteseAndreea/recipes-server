package com.recipes.api.dtos;

import com.recipes.api.entity.UserRecommendationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRecommendationDto {
    private Integer id;
    private RecipeDto breakfastRecipe;
    private RecipeDto lunchRecipe;
    private RecipeDto dinnerRecipe;

    private LocalDateTime dateTime;

    public static UserRecommendationDto fromEntity(final UserRecommendationEntity userRecommendation) {
        return UserRecommendationDto.builder()
                .id(userRecommendation.getId())
                .breakfastRecipe(RecipeDto.fromEntity(userRecommendation.getBreakfastRecipeId()))
                .lunchRecipe(RecipeDto.fromEntity(userRecommendation.getLunchRecipeId()))
                .dinnerRecipe(RecipeDto.fromEntity(userRecommendation.getDinnerRecipeId()))
                .dateTime(userRecommendation.getDateTime())
                .build();
    }
}
