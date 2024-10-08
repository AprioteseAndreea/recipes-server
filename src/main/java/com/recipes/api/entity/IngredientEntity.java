package com.recipes.api.entity;


import com.recipes.api.entity.enums.IngredientCategory;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ingredient", schema = "public", catalog = "recipesapp")
public class IngredientEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @Basic
    @Column(name = "expensive_index")
    private Integer expensiveIndex;

    @Basic
    @Column(name = "picture_url")
    private String pictureUrl;

    @Basic
    @Column(name = "ingredient_category")
    private IngredientCategory ingredientCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<RecipeIngredientEntity> recipeIngredientEntityList;

    @OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserIngredientEntity> ingredientCarts;
}
