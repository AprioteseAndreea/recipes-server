package com.recipes.api.repository;

import com.recipes.api.entity.IngredientImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientImageRepository extends JpaRepository<IngredientImageEntity, String> {
}
