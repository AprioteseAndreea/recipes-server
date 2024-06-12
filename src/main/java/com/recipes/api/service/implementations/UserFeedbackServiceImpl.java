package com.recipes.api.service.implementations;

import com.recipes.api.dtos.RecipeUserDto;
import com.recipes.api.entity.RecipeEntity;
import com.recipes.api.entity.UserEntity;
import com.recipes.api.entity.RecipeUserEntity;
import com.recipes.api.repository.RecipeRepository;
import com.recipes.api.repository.RecipeUserRepository;
import com.recipes.api.repository.UserRepository;
import com.recipes.api.service.interfaces.UserFeedbackService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log
public class UserFeedbackServiceImpl implements UserFeedbackService {
    private final RecipeUserRepository recipeUserRepository;
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;


    @Override
    public RecipeUserDto addFeedback(RecipeUserDto recipeUserDto) {
        RecipeEntity recipeEntity = recipeRepository.findById(recipeUserDto.getRecipeId()).orElseThrow();
        UserEntity userEntity = userRepository.findById(recipeUserDto.getUserId()).orElseThrow();
        RecipeUserEntity recipeUserEntity = RecipeUserEntity.builder()
                .id(recipeUserDto.getId())
                .recipe(recipeEntity)
                .user(userEntity)
                .rating(recipeUserDto.getRating())
                .feedback(recipeUserDto.getFeedback())
                .build();

        return RecipeUserDto.fromEntity(recipeUserRepository.save(recipeUserEntity));
    }

    @Override
    public RecipeUserDto editFeedback(RecipeUserDto recipeUserDto) {
        return null;
    }

    @Override
    public void deleteFeedback(Integer id) {

    }

    @Override
    public List<RecipeUserDto> getFeedbacksByRecipeId(Integer recipeId) {
        return recipeUserRepository
                .findUserFeedbackEntitiesByRecipe_Id(recipeId)
                .stream()
                .map(RecipeUserDto::fromEntity)
                .toList();
    }
}
