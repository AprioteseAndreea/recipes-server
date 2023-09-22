package com.recipes.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("OK!");
    }
}
