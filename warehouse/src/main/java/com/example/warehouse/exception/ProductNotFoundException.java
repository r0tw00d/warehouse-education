package com.example.warehouse.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String fieldName, String fieldValue) {
        super("Product with " + fieldName + " = " + fieldValue + " not found in database!");
    }
}
