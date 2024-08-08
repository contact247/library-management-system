package com.example.LMS.exceptions;

public class StudentNotFound extends RuntimeException{
    public StudentNotFound(String message){
        super(message);
    }
}
