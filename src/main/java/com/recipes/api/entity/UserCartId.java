package com.recipes.api.entity;

        import jakarta.persistence.Column;
        import jakarta.persistence.Embeddable;
        import lombok.*;


        import java.io.Serializable;

/**
 * It's a class that represents the composite primary key of the RecipeIngredient entity
 */
@Builder
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserCartId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "ingredient_id")
    private Long ingredientId;
}
