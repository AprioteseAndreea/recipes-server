package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.RecipeUserDto;

import java.util.List;

public interface UserFeedbackService {
    RecipeUserDto addFeedback(RecipeUserDto recipeUserDto);
    RecipeUserDto editFeedback(RecipeUserDto recipeUserDto);
    void deleteFeedback(Integer id);

    List<RecipeUserDto> getFeedbacksByRecipeId(Integer recipeId);
}
