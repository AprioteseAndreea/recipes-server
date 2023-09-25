package com.recipes.api.repository;

import com.recipes.api.entity.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {

    //List<IngredientEntity> findAllByExpensiveIndexAfterAndAndNameStartingWith(Integer index, String letter);
}
