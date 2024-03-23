package com.recipes.api.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class `BadRequestException` is a custom exception that represents a bad request and logs an
 * error message.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Slf4j
public class BadRequestException extends Exception {
    public BadRequestException(String errorMessage) {
        super(errorMessage);
        log.error(errorMessage);
    }
}
