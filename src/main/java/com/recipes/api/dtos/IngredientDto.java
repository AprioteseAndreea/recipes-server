package com.recipes.api.dtos;

import com.recipes.api.entity.DietEntity;
import com.recipes.api.entity.IngredientEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    private Integer id;

    @NotBlank(message = "Ingredient name is mandatory")
    @Size(max = 100, message = "The maximum length is 100 characters")
    private String name;

    private String pictureUrl;

    @Min(value = 0, message = "Index should not be less than 0")
    @Max(value = 100, message = "Index should not be greater than 18")
    private Integer expensiveIndex;

    public static IngredientDto fromEntity(final IngredientEntity ingredientEntity){
        return IngredientDto.builder()
                .id(ingredientEntity.getId())
                .name(ingredientEntity.getName())
                .pictureUrl(ingredientEntity.getPictureUrl())
                .expensiveIndex(ingredientEntity.getExpensiveIndex())
                .build();
    }
}
