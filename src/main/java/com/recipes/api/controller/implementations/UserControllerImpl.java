package com.recipes.api.controller.implementations;

import com.recipes.api.controller.interfaces.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements UserController {

    @Override
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("OK!");
    }
}
