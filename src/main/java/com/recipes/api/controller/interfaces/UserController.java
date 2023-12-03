package com.recipes.api.controller.interfaces;

import com.recipes.api.common.exceptions.BadRequestException;
import com.recipes.api.dtos.UserDto;
import com.recipes.api.dtos.UserIngredientDto;
import com.recipes.api.dtos.UserRecommendationDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer id);

    @GetMapping
    ResponseEntity<UserDto> getUserByEmail(@RequestParam String email);

    @GetMapping("/{id}/recommendations")
    ResponseEntity<UserRecommendationDto> getUserRecommendationByDate(@PathVariable Integer id, @RequestParam LocalDateTime dateTime);

    @GetMapping("/{id}/ingredients")
    ResponseEntity<List<UserIngredientDto>> getUserIngredientsByUserId(@PathVariable Integer id);

    @PostMapping("/{id}/ingredients")
    ResponseEntity<UserIngredientDto> postUserIngredient(@PathVariable Integer id, @Valid @RequestBody UserIngredientDto userIngredientDto) throws IllegalAccessException, BadRequestException;

}
