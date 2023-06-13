package com.example.warehouse.exception;

public class PriceNotFoundException extends RuntimeException{
    public PriceNotFoundException(String fieldName, String fieldValue) {
        super("Price with " + fieldName + " = " + fieldValue + " not found in database!");
    }
}
