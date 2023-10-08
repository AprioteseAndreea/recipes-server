package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.RecipeController;
import com.recipes.api.dtos.RecipeDto;
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
@RequestMapping("/recipes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeControllerImpl implements RecipeController {
    private final RecipeService recipeService;

    @Override
    public ResponseEntity<List<RecipeDto>> getRecipes() {
        List<RecipeDto> recipeDtos = recipeService.getRecipes();
        HttpStatus status = ObjectUtils.isNotEmpty(recipeDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(recipeDtos, status);
    }

    @Override
    public ResponseEntity<RecipeDto> getRecipeById(Integer id) {
        RecipeDto recipeDto = recipeService.getRecipeById(id);
        HttpStatus status = ObjectUtils.isNotEmpty(recipeDto) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(recipeDto, status);
    }
}
