package com.recipes.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIngredientsProfileDto {
    private Integer ingredientId;
    private Boolean isCartIngredient;
}
