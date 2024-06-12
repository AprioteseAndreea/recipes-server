package com.recipes.api.dtos;

import com.recipes.api.entity.RecipeUserEntity;
import com.recipes.api.entity.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeUserDto {
    private Integer id;
    private String feedback;
    private Integer rating;
    private Integer userId;
    private String userFirstName;
    private String userLastName;
    private Gender userGender;
    private Integer recipeId;
    private LocalDateTime dateTime;

    public static RecipeUserDto fromEntity(RecipeUserEntity recipeUserEntity){
        return RecipeUserDto.builder()
                .id(recipeUserEntity.getId())
                .feedback(recipeUserEntity.getFeedback())
                .rating(recipeUserEntity.getRating())
                .userId(UserDto.fromEntity(recipeUserEntity.getUser()).getId())
                .userFirstName(UserDto.fromEntity(recipeUserEntity.getUser()).getFirstName())
                .userLastName(UserDto.fromEntity(recipeUserEntity.getUser()).getLastName())
                .userGender(UserDto.fromEntity(recipeUserEntity.getUser()).getGender())
                .recipeId(RecipeDto.fromEntity(recipeUserEntity.getRecipe()).getId())
                .dateTime(recipeUserEntity.getDateTime())
                .build();
    }
}
