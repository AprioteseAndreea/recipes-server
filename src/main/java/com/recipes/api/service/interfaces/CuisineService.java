package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.CuisineDto;

import java.util.List;

public interface CuisineService {
    List<CuisineDto> getCuisines();

    CuisineDto getCuisineById(Integer id);

    CuisineDto addCuisine(CuisineDto cuisineDto);

    CuisineDto editCuisine(CuisineDto cuisineDto);
}
