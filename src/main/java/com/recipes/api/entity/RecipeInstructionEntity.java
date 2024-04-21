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
@Table(name = "recipe_instruction", schema = "public", catalog = "recipesapp")
public class RecipeInstructionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @MapsId("recipeId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RecipeEntity recipe;

    @Basic
    @Column(name = "details")
    private String details;

    @Basic
    @Column(name = "step")
    private Integer step;
}
