package com.example.warehouse.exception.handler;

import com.example.warehouse.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    protected ErrorResponse handleProductNotFoundException(Exception e) {
        return ErrorResponse
                .builder(e, HttpStatus.NOT_FOUND, e.getMessage())
                .build();
    }

    @ExceptionHandler(value = SQLException.class)
    protected ErrorResponse handleDatabaseException(SQLException e) {
        return ErrorResponse
                .builder(e, HttpStatus.BAD_REQUEST, e.getMessage())
                .build();
    }
}
