package com.recipes.api.dtos;

import com.recipes.api.entity.UserIngredientEntity;
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

    private Integer id;
    private IngredientDto ingredient;

    @PositiveOrZero
    private Double quantity;
    private UnitOfMeasure unitOfMeasure;
    private Boolean isCartIngredient = false;

    public static UserIngredientDto fromEntity(final UserIngredientEntity userIngredientEntity){
        return UserIngredientDto.builder()
                .id(userIngredientEntity.getId())
                .ingredient(IngredientDto.fromEntity(userIngredientEntity.getIngredient()))
                .quantity(userIngredientEntity.getQuantity())
                .unitOfMeasure(userIngredientEntity.getUnitOfMeasure())
                .isCartIngredient(userIngredientEntity.getIsCartIngredient())
                .build();
    }
}
