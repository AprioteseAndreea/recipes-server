package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.DietDto;

import java.util.List;

public interface DietService {
    List<DietDto> getDiets();

    DietDto getDietById(Integer id);
}
