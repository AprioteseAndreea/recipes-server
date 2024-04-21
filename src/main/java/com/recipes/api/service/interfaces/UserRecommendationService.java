package com.recipes.api.service.interfaces;

import com.recipes.api.dtos.UserRecommendationDto;

import java.time.LocalDateTime;
import java.util.List;

public interface UserRecommendationService {
    UserRecommendationDto getUserRecommendationsByDate(Integer id, LocalDateTime dateTime);
    List<UserRecommendationDto> getUserRecommendationsByInterval(Integer id, LocalDateTime startDate, LocalDateTime endDate);


}
