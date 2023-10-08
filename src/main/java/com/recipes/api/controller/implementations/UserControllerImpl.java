package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.UserController;
import com.recipes.api.dtos.UserDto;
import com.recipes.api.dtos.UserRecommendationDto;
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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserControllerImpl implements UserController {

    private final UserService userService;
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
}
