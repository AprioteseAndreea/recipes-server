package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.IngredientController;
import com.recipes.api.dtos.IngredientDto;
import com.recipes.api.service.interfaces.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientControllerImpl implements IngredientController {

    private final IngredientService ingredientService;

    @Override
    public ResponseEntity<List<IngredientDto>> getIngredients() {
        List<IngredientDto> ingredientDtos = ingredientService.getIngredients();
        HttpStatus status = ObjectUtils.isNotEmpty(ingredientDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(ingredientDtos, status);
    }


    @Override
    public ResponseEntity<IngredientDto> getIngredientById(Integer id) {
        IngredientDto ingredientDto = ingredientService.getIngredientById(id);
        HttpStatus status = ObjectUtils.isNotEmpty(ingredientDto) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(ingredientDto, status);
    }
}
