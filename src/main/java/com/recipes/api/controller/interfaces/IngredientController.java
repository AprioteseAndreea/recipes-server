package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.IngredientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IngredientController {
    @GetMapping
    ResponseEntity<List<IngredientDto>> getIngredients();

    @GetMapping("/{id}")
    ResponseEntity<IngredientDto> getIngredientById(@PathVariable Integer id);

}
