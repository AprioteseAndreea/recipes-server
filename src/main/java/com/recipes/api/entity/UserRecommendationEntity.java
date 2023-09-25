package com.recipes.api.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_recommendation", schema = "public", catalog = "recipesapp")
public class UserRecommendationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "breakfast_recipe_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RecipeEntity breakfastRecipeId;

    @ManyToOne
    @JoinColumn(name = "lunch_recipe_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private RecipeEntity lunchRecipeId;

    @ManyToOne
    @JoinColumn(name = "dinner_recipe_id")
    private RecipeEntity dinnerRecipeId;

    @Basic
    @Column(name = "date_time")
    private LocalDateTime dateTime;
}
