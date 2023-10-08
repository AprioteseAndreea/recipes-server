package com.recipes.api.repository;

import com.recipes.api.entity.UserRecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRecommendationsRepository extends JpaRepository<UserRecommendationEntity, Integer> {
   Optional<UserRecommendationEntity> findByDateTimeIsAndUserId(LocalDateTime dateTime, Integer id);
}
