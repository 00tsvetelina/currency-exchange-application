package com.currencyexchangeapp.currencyexchange.error;

public class OperationFailedException extends RuntimeException{
    public OperationFailedException(String message) {
        super(message);
    }
}
