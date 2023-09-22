package com.recipes.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipes.api.entity.enums.CookingLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "recipe", schema = "public", catalog = "recipesapp")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @Basic
    @Column(name = "prep_time")
    private Integer prepTime;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "cooking_level")
    private CookingLevel cookingLevel;

    @Basic
    @Column(name = "instructions")
    private String instructions;

    @Basic
    @Column(name = "picture_url")
    private String pictureUrl;

    @Basic
    @Column(name = "kcals")
    private Integer kcals;

    @Basic
    @Column(name = "favourites")
    private Integer favourites;

    @ManyToOne
    @JoinColumn(name = "cuisine_id")
    private CuisineEntity cuisine;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "recipe_diet",
            joinColumns = @JoinColumn(name = "recipe_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<DietEntity> recipeDiets;

    @JsonIgnore
    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<RecipeIngredientEntity> recipeIngredientEntityList;

    @OneToMany(mappedBy = "breakfastRecipeId", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserRecommendation> userBreakfastRecommendations;

    @OneToMany(mappedBy = "lunchRecipeId", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserRecommendation> userLunchRecommendations;

    @OneToMany(mappedBy = "dinnerRecipeId", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserRecommendation> userDinnerRecommendations;
}
