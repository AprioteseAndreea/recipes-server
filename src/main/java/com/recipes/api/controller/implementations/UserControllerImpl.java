package com.recipes.api.controller.implementations;

import com.recipes.api.common.ControllerUtils;
import com.recipes.api.common.exceptions.BadRequestException;
import com.recipes.api.controller.interfaces.UserController;
import com.recipes.api.dtos.UserDto;
import com.recipes.api.dtos.UserIngredientDto;
import com.recipes.api.dtos.UserRecommendationDto;
import com.recipes.api.exceptions.NotFoundException;
import com.recipes.api.service.interfaces.UserIngredientService;
import com.recipes.api.service.interfaces.UserRecommendationService;
import com.recipes.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.recipes.api.common.Constants.INVALID_REQUEST_BODY;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final UserIngredientService userIngredientService;
    private final UserRecommendationService userRecommendationService;

    @Override
    public ResponseEntity<UserDto> getUserById(Integer id) {

        UserDto userDto = userService.getUserById(id);
        HttpStatus status = ObjectUtils.isNotEmpty(userDto) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(userDto, status);
    }

    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) {

        UserDto userDto = userService.getUserByEmail(email);
        HttpStatus status = ObjectUtils.isNotEmpty(userDto) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(userDto, status);
    }

    @Override
    public ResponseEntity<UserRecommendationDto> getUserRecommendationByDate(Integer id, LocalDateTime dateTime) {
        UserRecommendationDto userRecommendationDtos = userRecommendationService.getUserRecommendationsByDate(id, dateTime);
        HttpStatus status = ObjectUtils.isNotEmpty(userRecommendationDtos) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(userRecommendationDtos, status);
    }

    @Override
    public ResponseEntity<List<UserIngredientDto>> getUserIngredientsByUserId(Integer id) {
        List<UserIngredientDto> userIngredientDtoList = userIngredientService.getUserIngredients(id);
        HttpStatus status = ObjectUtils.isNotEmpty(userIngredientDtoList) ? HttpStatus.OK : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(userIngredientDtoList, status);
    }

    @Override
    public ResponseEntity<UserIngredientDto> postUserIngredient(Integer id, UserIngredientDto userIngredientDto) throws IllegalAccessException, BadRequestException {
        if (!ControllerUtils.isValidRequestBody(userIngredientDto)) {
            throw new BadRequestException(INVALID_REQUEST_BODY);
        }

        try {
            UserIngredientDto createdUserIngredient = userIngredientService.addUserIngredient(userIngredientDto, id);
            // Assuming you want to return a 201 Created status along with the created UserIngredientDto
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUserIngredient);
        } catch (NotFoundException e) {
            // Handle the case when either ingredient or user is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Handle other exceptions or errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
