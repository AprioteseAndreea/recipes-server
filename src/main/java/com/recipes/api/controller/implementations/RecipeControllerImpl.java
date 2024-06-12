package com.recipes.api.controller.implementations;

import com.recipes.api.common.ControllerUtils;
import com.recipes.api.common.exceptions.BadRequestException;
import com.recipes.api.controller.interfaces.RecipeController;
import com.recipes.api.dtos.RecipeDto;
import com.recipes.api.dtos.RecipeUserDto;
import com.recipes.api.service.interfaces.RecipeService;
import com.recipes.api.service.interfaces.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.recipes.api.common.Constants.INVALID_REQUEST_BODY;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeControllerImpl implements RecipeController {
    private final RecipeService recipeService;
    private final UserFeedbackService userFeedbackService;


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

    @Override
    public ResponseEntity<RecipeUserDto> postUserFeedback(Integer id, RecipeUserDto recipeUserDto) throws IllegalAccessException, BadRequestException {
        if (!ControllerUtils.isValidRequestBody(recipeUserDto)) {
            throw new BadRequestException(INVALID_REQUEST_BODY);
        }

        return new ResponseEntity<>(userFeedbackService.addFeedback(recipeUserDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<RecipeUserDto>> getFeedbacksByRecipeId(Integer id) {
        List<RecipeUserDto> recipeUserDtos = userFeedbackService.getFeedbacksByRecipeId(id);
        HttpStatus status = ObjectUtils.isNotEmpty(recipeUserDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(recipeUserDtos, status);
    }
}
