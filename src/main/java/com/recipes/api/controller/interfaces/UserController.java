package com.recipes.api.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface UserController {
    @GetMapping()
    ResponseEntity<String> getUser();
}
