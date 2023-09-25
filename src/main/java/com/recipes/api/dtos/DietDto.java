package com.recipes.api.dtos;

import com.recipes.api.entity.DietEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DietDto {
    private Integer id;

    @NotBlank(message = "Diet name is mandatory")
    @Size(max = 100, message = "The maximum length is 100 characters")
    private String name;

    public static DietDto fromEntity(final DietEntity dietEntity){
        return DietDto.builder()
                .id(dietEntity.getId())
                .name(dietEntity.getName())
                .build();
    }
}
