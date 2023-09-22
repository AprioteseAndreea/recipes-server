package com.recipes.api.entity;

import com.recipes.api.entity.enums.CookingLevel;
import com.recipes.api.entity.enums.Gender;
import com.recipes.api.entity.enums.PhysicalEffort;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user", schema = "public", catalog = "recipesapp")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "first_name", nullable = false, length = 35)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 35)
    private String lastName;

    @Basic
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Basic
    @Column(name = "age")
    private Integer age;

    @Basic
    @Column(name = "height")
    private Integer height;

    @Basic
    @Column(name = "weight")
    private Integer weight;

    @Basic
    @Column(name = "bms")
    private Double bms;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "physical_effort")
    private PhysicalEffort physicalEffort;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "cooking_level")
    private CookingLevel cookingLevel;

    @Basic
    @Column(name = "want_to_learn_new_skills", nullable = false)
    @Builder.Default
    private Boolean wantToLearnNewSkills = false;

    @Basic
    @Column(name = "want_to_try_new_cuisines", nullable = false)
    @Builder.Default
    private Boolean wantToTryNewCuisines = false;

    @Basic
    @Column(name = "want_to_save_money", nullable = false)
    @Builder.Default
    private Boolean wantToSaveMoney = false;

    @Basic
    @Column(name = "want_to_save_time", nullable = false)
    @Builder.Default
    private Boolean wantToSaveTime = false;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_cuisine",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<CuisineEntity> userCuisines;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_diet",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diet_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<DietEntity> userDiets;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_disliked_ingredient",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<IngredientEntity> userDislikedIngredients;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<UserCartEntity> userCartEntityList;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<UserRecommendation> userRecommendations;

}
