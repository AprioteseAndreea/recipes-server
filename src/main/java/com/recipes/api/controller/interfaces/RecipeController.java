package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.RecipeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface RecipeController {
    @GetMapping
    ResponseEntity<List<RecipeDto>> getRecipes();

    @GetMapping("/{id}")
    ResponseEntity<RecipeDto> getRecipeById(@PathVariable Integer id);
}
