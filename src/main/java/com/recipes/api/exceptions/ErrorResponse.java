package com.recipes.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The class ErrorResponse is a class that represents an error response and contains a message field.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
}
