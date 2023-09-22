package com.recipes.api.dtos;

import com.recipes.api.entity.CuisineEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuisineDto {
    private Long id;

    @NotNull(message = "Cuisine name is mandatory")
    @Size(max = 100, message = "The maximum length is 100 characters")
    private String name;

    public static CuisineDto fromEntity(final CuisineEntity cuisineEntity){
        return CuisineDto.builder()
                .id(cuisineEntity.getId())
                .name(cuisineEntity.getName())
                .build();
    }
}
