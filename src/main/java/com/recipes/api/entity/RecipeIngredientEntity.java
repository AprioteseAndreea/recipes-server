package com.recipes.api.entity;

import com.recipes.api.entity.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recipe_ingredient", schema = "public", catalog = "recipesapp")
public class RecipeIngredientEntity implements Serializable {

    // A composite primary key.
    @EmbeddedId
    private RecipeIngredientId id;

    @ManyToOne
    @MapsId("recipeId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RecipeEntity recipe;

    @ManyToOne
    @MapsId("ingredientId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IngredientEntity ingredient;

    @Basic
    @Column(name = "quantity")
    private Double quantity;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measure")
    private UnitOfMeasure unitOfMeasure;
}
