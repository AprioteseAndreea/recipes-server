package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.UserDto;
import com.recipes.api.dtos.UserRecommendationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer id);

    @GetMapping
    ResponseEntity<UserDto> getUserByEmail(@RequestParam String email);

    @GetMapping("/{id}/recommendations")
    ResponseEntity<UserRecommendationDto> getUserRecommendationByDate(@PathVariable Integer id, @RequestParam LocalDateTime dateTime);


}
