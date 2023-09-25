package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.CuisineDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CuisineController {
    @GetMapping
    ResponseEntity<List<CuisineDto>> getCuisines();

    @GetMapping("/{id}")
    ResponseEntity<CuisineDto> getCuisineById(@PathVariable Integer id);
}
