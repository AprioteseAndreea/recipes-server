package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.UserIngredientDto;

import java.util.List;

public interface UserIngredientService {
    List<UserIngredientDto> getUserIngredients(Integer userId);
    UserIngredientDto addUserIngredient(UserIngredientDto userIngredientDto, Integer userId);
}
