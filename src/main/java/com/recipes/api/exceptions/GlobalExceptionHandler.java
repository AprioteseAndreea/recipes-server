package com.recipes.api.exceptions;

import com.recipes.api.common.Constants;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * The GlobalExceptionHandler class is a controller advice class that handles exceptions and returns a custom
 * error response.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = AsyncRequestTimeoutException.class)
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ResponseEntity<ErrorResponse> handlerException(AsyncRequestTimeoutException ex) {
        log.info("AsyncRequestTimeoutException occurred. Responding with 408 REQUEST_TIMEOUT");
        return new ResponseEntity<>(null, null, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(value = BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(BadRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, ex.getMessage());
        headers.add(Constants.X_ERROR_CODE, "400");
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(UnauthorizedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, ex.getMessage());
        headers.add(Constants.X_ERROR_CODE, "401");
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseEntity<ErrorResponse> handleException(NotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, ex.getMessage());
        headers.add(Constants.X_ERROR_CODE, "404");
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
            sb.append("field:").append(error.getField()).append(";error :").append(error.getDefaultMessage());
        });
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, sb.toString());
        headers.add(Constants.X_ERROR_CODE, "400");
        return new ResponseEntity<>(errors, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, ex.getMessage());
        headers.add(Constants.X_ERROR_CODE, "400");
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, ex.getMessage());
        headers.add(Constants.X_ERROR_CODE, "400");
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<ErrorResponse> handleDateTimeParseException(DateTimeParseException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add(Constants.X_ERROR_MESSAGE, ex.getMessage());
        headers.add(Constants.X_ERROR_CODE, "400");
        return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
    }
}
