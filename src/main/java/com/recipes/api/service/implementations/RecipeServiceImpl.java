package com.recipes.api.service.implementations;

import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.RecipeSummaryDto;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.RecipeRepository;
import com.recipes.api.service.interfaces.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.recipes.api.common.Constants.RECIPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    @Override
    public List<RecipeDto> getRecipes() {
        return recipeRepository.findAll().stream().map(RecipeDto::fromEntity).toList();
    }

    @Override
    public List<RecipeSummaryDto> getRecipesSummary() {
        return recipeRepository.findAll().stream().map(RecipeSummaryDto::fromEntity).toList();
    }

    @Override
    public RecipeDto getRecipeById(Integer id) {
        return recipeRepository
                .findById(id)
                .map(RecipeDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, id)));

    }
}
