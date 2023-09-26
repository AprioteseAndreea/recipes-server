package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.IngredientImageDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IngredientImageController {
    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file);

    @GetMapping
    public List<IngredientImageDto> list();

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id);
}
