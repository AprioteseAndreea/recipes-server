package com.recipes.api.repository;

import com.recipes.api.entity.UserIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserIngredientRepository extends JpaRepository<UserIngredientEntity, Integer> {
    List<UserIngredientEntity> findUserIngredientEntitiesByUser_Id(Integer userId);
    List<UserIngredientEntity> findUserIngredientEntitiesByUser_IdAndIngredient_Id(Integer userId, Integer ingredientId);
}
