package com.recipes.api.controller.interfaces;

import com.recipes.api.common.exceptions.BadRequestException;
import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.RecipeUserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RecipeController {
    @GetMapping
    ResponseEntity<List<RecipeDto>> getRecipes();

    @GetMapping("/{id}")
    ResponseEntity<RecipeDto> getRecipeById(@PathVariable Integer id);

    @PostMapping("/{id}/feedback")
    ResponseEntity<RecipeUserDto> postUserFeedback(@PathVariable Integer id, @Valid @RequestBody RecipeUserDto recipeUserDto) throws IllegalAccessException, BadRequestException;

    @GetMapping("/{id}/feedback")
    ResponseEntity<List<RecipeUserDto>> getFeedbacksByRecipeId(@PathVariable Integer id);
}
