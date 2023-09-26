package com.recipes.api.service.implementations;

import com.recipes.api.entity.IngredientImageEntity;
import com.recipes.api.repository.IngredientImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class IngredientImageService {
    private final IngredientImageRepository ingredientImageRepository;

    public void save(MultipartFile file) throws IOException {
        IngredientImageEntity fileEntity = new IngredientImageEntity();
        fileEntity.setName(StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
        fileEntity.setContentType(file.getContentType());
        fileEntity.setData(file.getBytes());
        fileEntity.setSize(file.getSize());

        ingredientImageRepository.save(fileEntity);
    }

    public Optional<IngredientImageEntity> getFile(String id) {
        return ingredientImageRepository.findById(id);
    }

    public List<IngredientImageEntity> getAllFiles() {
        return ingredientImageRepository.findAll();
    }


}
