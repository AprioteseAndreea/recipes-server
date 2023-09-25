package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.DietDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface DietController {
    @GetMapping
    ResponseEntity<List<DietDto>> getDiets();

    @GetMapping("/{id}")
    ResponseEntity<DietDto> getDietById(@PathVariable Integer id);
}
