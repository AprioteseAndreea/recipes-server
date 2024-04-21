package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.RecipeSummaryDto;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> getRecipes();
    List<RecipeSummaryDto> getRecipesSummary();

    RecipeDto getRecipeById(Integer id);
}
