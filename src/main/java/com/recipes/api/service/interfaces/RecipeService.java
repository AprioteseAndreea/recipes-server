package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.DietDto;
import com.recipes.api.dtos.RecipeDto;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> getRecipes();

    RecipeDto getRecipeById(Integer id);
}
