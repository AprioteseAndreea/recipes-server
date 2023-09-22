package com.recipes.api.dtos;

import com.recipes.api.entity.enums.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.PositiveOrZero;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIngredientDto {
    private Long id;
    private UserDto user;
    private IngredientDto ingredient;
    @PositiveOrZero
    private Double quantity;
    private UnitOfMeasure unitOfMeasure;
    private Boolean isCartIngredient = false;
}
