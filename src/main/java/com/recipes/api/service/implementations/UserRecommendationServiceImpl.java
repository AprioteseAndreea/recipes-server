package com.recipes.api.service.implementations;

import com.recipes.api.dtos.UserRecommendationDto;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.UserRecommendationsRepository;
import com.recipes.api.service.interfaces.UserRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.recipes.api.common.Constants.DIET_NOT_FOUND;
import static com.recipes.api.common.Constants.USER_RECOMMENDATION_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class UserRecommendationServiceImpl implements UserRecommendationService {
    private final UserRecommendationsRepository userRecommendationsRepository;

    @Override
    public UserRecommendationDto getUserRecommendationsByDate(Integer id, LocalDateTime dateTime) {
        return userRecommendationsRepository.findByDateTimeIsAndUserId(dateTime, id)
                .map(UserRecommendationDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(USER_RECOMMENDATION_NOT_FOUND)));
    }
}
