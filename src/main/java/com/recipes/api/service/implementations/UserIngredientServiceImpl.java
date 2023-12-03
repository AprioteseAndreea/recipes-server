package com.recipes.api.service.implementations;

import com.recipes.api.dtos.UserIngredientDto;
import com.recipes.api.entity.IngredientEntity;
import com.recipes.api.entity.UserEntity;
import com.recipes.api.entity.UserIngredientEntity;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.IngredientRepository;
import com.recipes.api.repository.UserIngredientRepository;
import com.recipes.api.repository.UserRepository;
import com.recipes.api.service.interfaces.UserIngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public class UserIngredientServiceImpl implements UserIngredientService {
    private final UserIngredientRepository userIngredientRepository;

    private final IngredientRepository ingredientRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserIngredientDto> getUserIngredients(Integer userId) {
        return userIngredientRepository
                .findUserIngredientEntitiesByUser_Id(userId)
                .stream()
                .map(UserIngredientDto::fromEntity).toList();
    }

    @Override
    public UserIngredientDto addUserIngredient(UserIngredientDto userIngredientDto, Integer userId) {
        Optional<IngredientEntity> ingredientEntityOptional = ingredientRepository.findById(userIngredientDto.getIngredient().getId());
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);

        if (ingredientEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            IngredientEntity ingredientEntity = ingredientEntityOptional.get();
            UserEntity userEntity = userEntityOptional.get();

            UserIngredientEntity userIngredientEntity = UserIngredientEntity.builder()
                    .ingredient(ingredientEntity)
                    .user(userEntity)
                    .quantity(userIngredientDto.getQuantity())
                    .unitOfMeasure(userIngredientDto.getUnitOfMeasure())
                    .isCartIngredient(userIngredientDto.getIsCartIngredient())
                    .build();

            return UserIngredientDto.fromEntity(userIngredientRepository.save(userIngredientEntity));
        } else {
            // Handle the case when either ingredient or user is not found
            throw new NotFoundException("Ingredient or User not found");
        }
    }

}
