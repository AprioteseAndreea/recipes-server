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

import java.util.Comparator;
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
                .map(UserIngredientDto::fromEntity)
                .sorted(Comparator.comparing(i -> i.getIngredient().getName()))
                .toList();
    }

    @Override
    public UserIngredientDto addUserIngredient(UserIngredientDto userIngredientDto, Integer userId) {
        IngredientEntity ingredientEntityOptional = ingredientRepository.findById(userIngredientDto.getIngredient().getId())
                .orElseThrow(() -> new NotFoundException("Ingredient not found"));

        UserEntity userEntityOptional = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        // Get all user's ingredients for a certain id
        List<UserIngredientEntity> similarIngredientsList = userIngredientRepository
                .findUserIngredientEntitiesByUser_IdAndIngredient_Id(userId, userIngredientDto.getIngredient().getId());

        // Check if we have an instance with the same unit of measure and isCart value
        Optional<UserIngredientEntity> existingUserIngredientOptional = similarIngredientsList.stream()
                .filter(ingredient -> ingredient.getUnitOfMeasure().equals(userIngredientDto.getUnitOfMeasure()) &&
                        ingredient.getIsCartIngredient() == userIngredientDto.getIsCartIngredient())
                .findFirst();

        UserIngredientEntity userIngredientEntity = UserIngredientEntity.builder()
                .ingredient(ingredientEntityOptional)
                .user(userEntityOptional)
                .quantity(userIngredientDto.getQuantity())
                .unitOfMeasure(userIngredientDto.getUnitOfMeasure())
                .isCartIngredient(userIngredientDto.getIsCartIngredient())
                .build();

        if (existingUserIngredientOptional.isPresent()) {
            userIngredientEntity.setQuantity(userIngredientEntity.getQuantity() + existingUserIngredientOptional.get().getQuantity());
            userIngredientEntity.setId(existingUserIngredientOptional.get().getId());
        }


        return UserIngredientDto.fromEntity(userIngredientRepository.save(userIngredientEntity));
    }

    @Override
    public UserIngredientDto updateUserIngredient(UserIngredientDto userIngredientDto, Integer userId) {

        // Retrieve existing user ingredient entity
        UserIngredientEntity existingUserIngredientEntity = userIngredientRepository
                .findById(userIngredientDto.getId())
                .orElseThrow(() -> new NotFoundException("UserIngredient not found"));

        existingUserIngredientEntity.setQuantity(userIngredientDto.getQuantity());
        existingUserIngredientEntity.setUnitOfMeasure(userIngredientDto.getUnitOfMeasure());
        existingUserIngredientEntity.setIsCartIngredient(userIngredientDto.getIsCartIngredient());

        UserIngredientEntity updatedEntity = userIngredientRepository.save(existingUserIngredientEntity);

        return UserIngredientDto.fromEntity(updatedEntity);
    }

    @Override
    public void deleteUserIngredient(Integer userIngrId) {
        UserIngredientEntity existingUserIngredientEntity = userIngredientRepository
                .findById(userIngrId)
                .orElseThrow(() -> new NotFoundException("UserIngredient not found"));

        userIngredientRepository.delete(existingUserIngredientEntity);
    }
}
