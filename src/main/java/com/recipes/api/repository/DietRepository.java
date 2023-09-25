package com.recipes.api.repository;

import com.recipes.api.entity.DietEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<DietEntity, Integer> {
}
