package com.recipes.api.entity;

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
@Table(name = "cuisine", schema = "public", catalog = "recipesapp")
public class CuisineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "cuisine", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<RecipeEntity> cuisineRecipes;
}
