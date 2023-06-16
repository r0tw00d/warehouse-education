package com.example.orderservice.exception;

public class ProductLeftoverNotEnoughException extends RuntimeException{
    public ProductLeftoverNotEnoughException(String fieldName, String fieldValue) {
        super("Product with " + fieldName + " = " + fieldValue + " not enough leftover!");
    }
}
