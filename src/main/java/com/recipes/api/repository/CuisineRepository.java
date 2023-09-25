package com.recipes.api.repository;

import com.recipes.api.entity.CuisineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<CuisineEntity, Integer> {
}
