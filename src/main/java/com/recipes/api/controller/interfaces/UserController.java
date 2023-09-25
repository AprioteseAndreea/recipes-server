package com.recipes.api.controller.interfaces;

import com.recipes.api.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Integer id);

    @GetMapping
    ResponseEntity<UserDto> getUserByEmail(@RequestParam String email);
}
