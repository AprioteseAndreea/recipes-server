package com.recipes.api.dtos;

import com.recipes.api.entity.IngredientEntity;
import com.recipes.api.entity.IngredientImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientImageDto {
    private String id;
    private String name;
    private Long size;
    private String url;
    private String contentType;

    public static IngredientImageDto fromEntity(final IngredientImageEntity ingredientImageEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(ingredientImageEntity.getId())
                .toUriString();

        IngredientImageDto ingredientImageDto = new IngredientImageDto();
        ingredientImageDto.setId(ingredientImageEntity.getId());
        ingredientImageDto.setName(ingredientImageEntity.getName());
        ingredientImageDto.setContentType(ingredientImageEntity.getContentType());
        ingredientImageDto.setSize(ingredientImageEntity.getSize());
        ingredientImageDto.setUrl(downloadURL);

        return ingredientImageDto;
    }
}
