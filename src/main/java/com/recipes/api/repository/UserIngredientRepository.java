package com.recipes.api.repository;

import com.recipes.api.entity.UserIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserIngredientRepository extends JpaRepository<UserIngredientEntity, Integer> {
    List<UserIngredientEntity> findUserIngredientEntitiesByUser_Id(Integer userId);
}
