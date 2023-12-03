package com.recipes.api.repository;

import com.recipes.api.entity.RecipeEntity;
import com.recipes.api.entity.UserEntity;
import com.recipes.api.entity.UserIngredientEntity;
import com.recipes.api.entity.UserRecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    //List<IngredientEntity> findAllByExpensiveIndexAfterAndAndNameStartingWith(Integer index, String letter);
    UserEntity findByEmailIs(String email);
}
