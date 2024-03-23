package com.recipes.api.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
@Slf4j
public class UnauthorizedException extends Exception {
    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
        log.error(errorMessage);
    }
}
