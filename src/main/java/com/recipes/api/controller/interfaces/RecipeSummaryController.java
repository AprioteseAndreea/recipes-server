package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.RecipeSummaryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface RecipeSummaryController {
    @GetMapping
    ResponseEntity<List<RecipeSummaryDto>> getRecipesSummary();
}
