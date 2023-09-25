package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.IngredientDto;

import java.util.List;

public interface IngredientService {
    List<IngredientDto> getIngredients();

    IngredientDto getIngredientById(Integer id);
}
