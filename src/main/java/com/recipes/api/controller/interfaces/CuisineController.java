package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.CuisineDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CuisineController {
    @GetMapping
    ResponseEntity<List<CuisineDto>> getCuisines();

    @GetMapping("/{id}")
    ResponseEntity<CuisineDto> getCuisineById(@PathVariable Integer id);

    @PostMapping
    ResponseEntity<CuisineDto> addCuisine(@Valid @RequestBody CuisineDto cuisineDto);

    @PatchMapping
    ResponseEntity<CuisineDto> editCuisine(@Valid @RequestBody CuisineDto cuisineDto);
}
