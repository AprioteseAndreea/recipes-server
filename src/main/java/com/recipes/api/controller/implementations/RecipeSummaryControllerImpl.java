package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.RecipeSummaryController;
import com.recipes.api.dtos.RecipeSummaryDto;
import com.recipes.api.service.interfaces.RecipeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes-summary")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeSummaryControllerImpl implements RecipeSummaryController {
    private final RecipeService recipeService;
    @Override
    public ResponseEntity<List<RecipeSummaryDto>> getRecipesSummary() {
        List<RecipeSummaryDto> recipeDtos = recipeService.getRecipesSummary();
        HttpStatus status = ObjectUtils.isNotEmpty(recipeDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(recipeDtos, status);
    }
}
