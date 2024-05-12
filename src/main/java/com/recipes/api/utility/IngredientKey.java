package com.recipes.api.utility;

import com.recipes.api.entity.enums.UnitOfMeasure;
import lombok.*;

import java.util.Objects;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IngredientKey {
    private Integer ingredientId;
    private UnitOfMeasure unitOfMeasure;

    // Metode equals și hashCode pentru a asigura corectitudinea funcționării map-ului
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientKey that = (IngredientKey) o;
        return Objects.equals(ingredientId, that.ingredientId) &&
                unitOfMeasure == that.unitOfMeasure;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, unitOfMeasure);
    }

}
