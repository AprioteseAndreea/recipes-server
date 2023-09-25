package com.recipes.api.service.implementations;

import com.recipes.api.dtos.DietDto;
import com.recipes.api.dtos.IngredientDto;
import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.UserDto;
import com.recipes.api.entity.UserEntity;
import com.recipes.api.repository.RecipeRepository;
import com.recipes.api.service.interfaces.RecipeService;
import com.recipes.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public RecipeDto getRecipeById(Integer id) {
        return recipeRepository.findById(id).map(RecipeDto::fromEntity).get();

    }
}
