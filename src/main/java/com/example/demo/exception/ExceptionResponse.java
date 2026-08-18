package com.example.demo.exception;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ExceptionResponse {
    Instant timestamp;
    int code;
    String path;
    String error;
    String message;
}
