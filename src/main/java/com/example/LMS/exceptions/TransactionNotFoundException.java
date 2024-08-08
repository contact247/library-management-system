package com.example.LMS.exceptions;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message){
        super(message);
    }
}
