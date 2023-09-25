package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.CuisineDto;
import com.recipes.api.dtos.IngredientDto;

import java.util.List;

public interface CuisineService {
    List<CuisineDto> getCuisines();

    CuisineDto getCuisineById(Integer id);
}
