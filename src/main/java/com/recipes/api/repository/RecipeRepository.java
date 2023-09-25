package com.recipes.api.repository;

import com.recipes.api.dtos.DietDto;
import com.recipes.api.entity.RecipeEntity;
import com.recipes.api.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

}
