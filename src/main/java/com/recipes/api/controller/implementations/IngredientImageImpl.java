package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.IngredientImageController;
import com.recipes.api.dtos.IngredientImageDto;
import com.recipes.api.entity.IngredientImageEntity;
import com.recipes.api.service.implementations.IngredientImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class IngredientImageImpl implements IngredientImageController {

    public final IngredientImageService ingredientImageService;

    @Override
    public ResponseEntity<String> upload(MultipartFile file) {
        try {
            ingredientImageService.save(file);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @Override
    public List<IngredientImageDto> list() {
        return ingredientImageService.getAllFiles()
                .stream()
                .map(IngredientImageDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<byte[]> getFile(String id) {
        Optional<IngredientImageEntity> fileEntityOptional = ingredientImageService.getFile(id);

        if (fileEntityOptional.isEmpty()) {
            return ResponseEntity.notFound()
                    .build();
        }

        IngredientImageEntity fileEntity = fileEntityOptional.get();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileEntity.getName() + "\"")
                .contentType(MediaType.valueOf(fileEntity.getContentType()))
                .body(fileEntity.getData());
    }
}
