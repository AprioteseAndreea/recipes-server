package com.recipes.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRecProfileDto {
    private Integer id;
    private LocalDateTime dateTime;

    private UserProfileRecipeDto breakfastRecipe;
    private UserProfileRecipeDto lunchRecipe;
    private UserProfileRecipeDto dinnerRecipe;
}
