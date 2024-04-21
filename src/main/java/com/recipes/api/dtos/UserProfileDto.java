package com.recipes.api.dtos;

import com.recipes.api.entity.IngredientEntity;
import com.recipes.api.entity.UserEntity;
import com.recipes.api.entity.enums.CookingLevel;
import com.recipes.api.entity.enums.Gender;
import com.recipes.api.entity.enums.PhysicalEffort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
    private Integer id;
    private String email;

    private Integer age;
    private Integer height;
    private Integer weight;

    private Double bms;

    private Gender gender;
    private CookingLevel cookingLevel;
    private PhysicalEffort physicalEffort;

    private Boolean wantToLearnNewSkills = false;
    private Boolean wantToTryNewCuisines = false;
    private Boolean wantToEatHealthy = false;
    private Boolean wantToSaveMoney = false;
    private Boolean wantToSaveTime = false;

    private List<DietDto> userDiets;
    private List<CuisineDto> userCuisines;

    private List<UserIngredientDto> userIngredients;
    private List<Integer> userDislikedIngredientsIds;
    private List<UserRecProfileDto> userRecommendations;

    public static UserProfileDto fromEntityWithIdsOnly(final UserEntity userEntity) {
        return UserProfileDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .age(userEntity.getAge())
                .height(userEntity.getHeight())
                .weight(userEntity.getWeight())
                .bms(userEntity.getBms())
                .gender(userEntity.getGender())
                .cookingLevel(userEntity.getCookingLevel())
                .physicalEffort(userEntity.getPhysicalEffort())
                .wantToLearnNewSkills(userEntity.getWantToLearnNewSkills())
                .wantToTryNewCuisines(userEntity.getWantToTryNewCuisines())
                .wantToSaveMoney(userEntity.getWantToSaveMoney())
                .wantToEatHealthy(userEntity.getWantToEatHealthy())
                .wantToSaveTime(userEntity.getWantToSaveTime())
                .userDiets(
                        userEntity.getUserDiets()
                                .stream()
                                .map(DietDto::fromEntity)
                                .toList())
                .userCuisines(
                        userEntity.getUserCuisines()
                                .stream()
                                .map(CuisineDto::fromEntity)
                                .toList())
                .userIngredients(
                        userEntity.getUserIngredientsList()
                                .stream()
                                .map(UserIngredientDto::fromEntity)
                                .toList())
                .userDislikedIngredientsIds(
                        userEntity.getUserDislikedIngredients()
                                .stream()
                                .map(IngredientEntity::getId)
                                .toList())
                .userRecommendations(
                        userEntity.getUserRecommendations()
                                .stream()
                                .map(userRecommendationDto -> UserRecProfileDto.builder()
                                        .id(userRecommendationDto.getId())
                                        .dateTime(userRecommendationDto.getDateTime())
                                        .breakfastRecipe(UserProfileRecipeDto.fromRecipeDto(userRecommendationDto.getBreakfastRecipeId()))
                                        .lunchRecipe(UserProfileRecipeDto.fromRecipeDto(userRecommendationDto.getLunchRecipeId()))
                                        .dinnerRecipe(UserProfileRecipeDto.fromRecipeDto(userRecommendationDto.getDinnerRecipeId()))
                                        .build())
                                .toList())
                .build();
    }
}
