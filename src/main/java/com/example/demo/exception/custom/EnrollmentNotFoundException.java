package com.example.demo.exception.custom;

public class EnrollmentNotFoundException extends RuntimeException{
    public EnrollmentNotFoundException(String message){
        super(message);
    }
}
