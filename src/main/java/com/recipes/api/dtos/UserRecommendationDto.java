package com.recipes.api.dtos;

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
    private Long id;
    private UserDto user;
    private RecipeDto breakfastRecipe;
    private RecipeDto lunchRecipe;
    private RecipeDto dinnerRecipe;

    @FutureOrPresent
    private LocalDateTime dateTime;

}
