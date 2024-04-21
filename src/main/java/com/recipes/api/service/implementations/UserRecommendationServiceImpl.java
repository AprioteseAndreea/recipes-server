package com.recipes.api.service.implementations;

import com.recipes.api.dtos.UserDto;
import com.recipes.api.dtos.UserProfileDto;
import com.recipes.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.recipes.api.dtos.UserRecommendationDto;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.UserRecommendationsRepository;
import com.recipes.api.service.interfaces.UserRecommendationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.recipes.api.common.Constants.*;

@Service
@RequiredArgsConstructor
@Log
public class UserRecommendationServiceImpl implements UserRecommendationService {
    private final UserRecommendationsRepository userRecommendationsRepository;
    private final UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public UserRecommendationDto getUserRecommendationsByDate(Integer id, LocalDateTime dateTime) {
        return userRecommendationsRepository.findByDateTimeIsAndUserId(dateTime, id)
                .map(UserRecommendationDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(USER_RECOMMENDATION_NOT_FOUND)));
    }

    @Override
    public List<UserRecommendationDto> getUserRecommendationsByInterval(Integer id, LocalDateTime startDate, LocalDateTime endDate) {
        UserProfileDto userDto = userRepository
                .findById(id)
                .map(UserProfileDto::fromEntityWithIdsOnly)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("userDto", userDto);
        requestBody.put("startDate", startDate);
        requestBody.put("endDate", endDate);

        String url = "http://localhost:5000/api/user";

        ResponseEntity<UserRecommendationDto[]> responseEntity = restTemplate.postForEntity(url, requestBody, UserRecommendationDto[].class);

        UserRecommendationDto[] userRecommendationDtos = responseEntity.getBody();

        return Arrays.asList(userRecommendationDtos);
    }
}
