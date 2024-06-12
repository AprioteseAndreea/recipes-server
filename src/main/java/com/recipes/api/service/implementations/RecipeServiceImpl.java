package com.recipes.api.service.implementations;

import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.RecipeUserDto;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.RecipeRepository;
import com.recipes.api.service.interfaces.RecipeService;
import com.recipes.api.service.interfaces.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;

import static com.recipes.api.common.Constants.RECIPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    private final UserFeedbackService userFeedbackService;

    @Override
    public List<RecipeDto> getRecipes() {
        List<RecipeDto> recipeDtos = recipeRepository.findAll().stream().map(RecipeDto::fromEntity).toList();
        recipeDtos.forEach(recipeDto -> recipeDto.setRating(calculateAverageRating(recipeDto.getId())));
        return recipeDtos;
    }

    @Override
    public RecipeDto getRecipeById(Integer id) {
        RecipeDto recipeDto = recipeRepository
                .findById(id)
                .map(RecipeDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(RECIPE_NOT_FOUND, id)));
        recipeDto.setRating(calculateAverageRating(id));
        return recipeDto;

    }

    public double calculateAverageRating(Integer recipeId) {
        List<RecipeUserDto> recipeUserDtos = userFeedbackService.getFeedbacksByRecipeId(recipeId);

        // Calcularea mediei rating-urilor folosind stream-uri
        OptionalDouble averageRating = recipeUserDtos.stream()
                .mapToInt(RecipeUserDto::getRating)
                .average();

        // Dacă există rating-uri, returnează media, altfel returnează 0
        return averageRating.isPresent() ? averageRating.getAsDouble() : 0;
    }
}
