package com.recipes.api.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
@Slf4j
public class InternalServerErrorException extends Exception {
    public InternalServerErrorException(String errorMessage) {
        super(errorMessage);
        log.error(errorMessage);
    }
}
