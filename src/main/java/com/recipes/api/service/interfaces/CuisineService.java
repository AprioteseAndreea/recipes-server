package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.CuisineDto;
import com.recipes.api.dtos.IngredientDto;

import java.util.List;
import java.util.Optional;

public interface CuisineService {
    List<CuisineDto> getCuisines();

    Optional<CuisineDto> getCuisineById(Integer id);

    CuisineDto addCuisine(CuisineDto cuisineDto);

    CuisineDto editCuisine(CuisineDto cuisineDto);
}
