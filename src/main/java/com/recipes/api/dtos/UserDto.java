package com.recipes.api.dtos;

import com.recipes.api.entity.UserEntity;
import com.recipes.api.entity.enums.CookingLevel;
import com.recipes.api.entity.enums.Gender;
import com.recipes.api.entity.enums.PhysicalEffort;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer id;

    @Size(max = 35, message = "The maximum length is 100 characters")
    private String firstName;

    @Size(max = 35, message = "The maximum length is 100 characters")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email
    @Size(max = 100, message = "The maximum length is 100 characters")
    private String email;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private Integer age;

    @Min(value = 70, message = "The height should not be less than 70 cm")
    @Max(value = 250, message = "The height not be greater than 250 cm")
    private Integer height;

    @Min(value = 20, message = "The weight not be less than 20")
    @Max(value = 200, message = "The weight should not be greater than 200")
    private Integer weight;

    @PositiveOrZero
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
    private List<IngredientDto> userDislikedIngredients;
    private List<UserRecommendationDto> userRecommendations;

    public static UserDto fromEntity(final UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
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
                .userDislikedIngredients(
                        userEntity.getUserDislikedIngredients()
                        .stream()
                        .map(IngredientDto::fromEntity)
                        .toList())
                .userRecommendations(userEntity.getUserRecommendations()
                        .stream()
                        .map(UserRecommendationDto::fromEntity)
                        .toList())
                .build();
    }
}
