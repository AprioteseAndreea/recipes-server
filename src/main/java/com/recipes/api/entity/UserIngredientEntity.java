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
@Table(name = "user_ingredient", schema = "public", catalog = "recipesapp")
public class UserIngredientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IngredientEntity ingredient;

    @Basic
    @Column(name = "quantity")
    private Double quantity;

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "unit_of_measure")
    private UnitOfMeasure unitOfMeasure;

    //if is_cart is true the ingredient is from the cart
    //if is_cart is false the ingredient is from fridge
    @Basic
    @Column(name = "is_cart", nullable = false)
    @Builder.Default
    private Boolean isCartIngredient = false;
}