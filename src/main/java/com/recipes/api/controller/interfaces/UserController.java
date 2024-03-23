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

    @PatchMapping("/{id}")
    ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) throws IllegalAccessException, BadRequestException;

    @GetMapping
    ResponseEntity<UserDto> getUserByEmail(@RequestParam String email);

    @GetMapping("/{id}/recommendations")
    ResponseEntity<UserRecommendationDto> getUserRecommendationByDate(@PathVariable Integer id, @RequestParam LocalDateTime dateTime);

    @GetMapping("/{id}/ingredients")
    ResponseEntity<List<UserIngredientDto>> getUserIngredientsByUserId(@PathVariable Integer id);

    @PostMapping("/{id}/ingredients")
    ResponseEntity<UserIngredientDto> postUserIngredient(@PathVariable Integer id, @Valid @RequestBody UserIngredientDto userIngredientDto) throws IllegalAccessException, BadRequestException;

    @PatchMapping("/{id}/ingredients")
    ResponseEntity<UserIngredientDto> updateUserIngredient(@PathVariable Integer id, @Valid @RequestBody UserIngredientDto userIngredientDto) throws IllegalAccessException, BadRequestException;

    @DeleteMapping("/{id}/ingredients/{ingrId}")
    ResponseEntity<String> deleteUserIngredient(@PathVariable Integer id, @PathVariable Integer ingrId);
}
