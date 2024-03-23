package com.recipes.api.service.implementations;

import com.recipes.api.dtos.*;
import com.recipes.api.entity.*;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.repository.UserRepository;
import com.recipes.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.recipes.api.common.Constants.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Log
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto getUserById(Integer id) {
        return userRepository
                .findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return UserDto.fromEntity(userRepository.findByEmailIs(email));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        System.out.println(userDto);
        UserEntity userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setGender(userDto.getGender());
        userEntity.setAge(userDto.getAge());
        userEntity.setHeight(userDto.getHeight());
        userEntity.setWeight(userDto.getWeight());
        userEntity.setBms(userDto.getBms());
        userEntity.setPhysicalEffort(userDto.getPhysicalEffort());
        userEntity.setCookingLevel(userDto.getCookingLevel());
        userEntity.setWantToLearnNewSkills(userDto.getWantToLearnNewSkills());
        userEntity.setWantToTryNewCuisines(userDto.getWantToTryNewCuisines());
        userEntity.setWantToSaveMoney(userDto.getWantToSaveMoney());
        userEntity.setWantToSaveTime(userDto.getWantToSaveTime());
        userEntity.setWantToEatHealthy(userDto.getWantToEatHealthy());

        List<CuisineEntity> cuisineEntities = userDto.getUserCuisines().stream()
                .map(cuisineDto -> modelMapper.map(cuisineDto, CuisineEntity.class))
                .collect(Collectors.toList());

        userEntity.setUserCuisines(cuisineEntities);

        List<DietEntity> dietsEntities = userDto.getUserDiets().stream()
                .map(dietsDto -> modelMapper.map(dietsDto, DietEntity.class))
                .collect(Collectors.toList());

        userEntity.setUserDiets(dietsEntities);

        System.out.println(userEntity);

//        List<IngredientEntity> ingredientEntities = userDto.getUserIngredients().stream()
//                .map(ingredientDto -> modelMapper.map(ingredientDto, IngredientEntity.class))
//                .toList();
//
//        userEntity.setUserDislikedIngredients(ingredientEntities);

        return UserDto.fromEntity(userRepository.save(userEntity));
    }

}
