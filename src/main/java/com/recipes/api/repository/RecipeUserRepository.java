package com.recipes.api.repository;

import com.recipes.api.entity.RecipeUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeUserRepository extends JpaRepository<RecipeUserEntity, Integer> {
    List<RecipeUserEntity> findUserFeedbackEntitiesByRecipe_Id(Integer recipeId);
    List<RecipeUserEntity> findUserFeedbackEntitiesByUser_Id(Integer userId);
    List<RecipeUserEntity> findUserFeedbackEntitiesByUser_IdAndRecipe_Id(Integer userId, Integer recipeId);
    void removeUserFeedbackEntitiesByUser_IdAndRecipe_Id(Integer userId, Integer recipeId);
}
