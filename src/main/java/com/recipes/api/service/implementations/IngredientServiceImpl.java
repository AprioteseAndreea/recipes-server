package com.recipes.api.service.implementations;

import com.recipes.api.dtos.IngredientDto;
import com.recipes.api.repository.IngredientRepository;
import com.recipes.api.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    public List<IngredientDto> getIngredients() {
        return ingredientRepository.findAll().stream().map(IngredientDto::fromEntity).toList();
    }

    @Override
    public IngredientDto getIngredientById(Integer id) {
        return ingredientRepository.findById(id).map(IngredientDto::fromEntity).get();
    }


}

