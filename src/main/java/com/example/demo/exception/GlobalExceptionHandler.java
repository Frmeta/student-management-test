package com.example.demo.exception;
import java.time.Instant;

import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.demo.exception.custom.EnrollmentNotFoundException;
import com.example.demo.exception.custom.StudentNotFoundException;
import com.example.demo.exception.custom.SubjectNotFoundException;
import com.mongodb.MongoTimeoutException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ExceptionResponse handleGeneralException(Exception ex, HttpServletRequest request){
        return new ExceptionResponse(Instant.now(), 404, request.getRequestURI(), "General Exception", ex.getMessage());
    }
    @ExceptionHandler(StudentNotFoundException.class)
    public ExceptionResponse handleStudentNotFoundException(Exception ex, HttpServletRequest request){
        return new ExceptionResponse(Instant.now(), 404, request.getRequestURI() , "Student not found", ex.getMessage());
    }
    @ExceptionHandler(SubjectNotFoundException.class)
    public ExceptionResponse handleSubjectNotFoundException(Exception ex, HttpServletRequest request){
        return new ExceptionResponse(Instant.now(), 404, request.getRequestURI() , "Subject Not Found", ex.getMessage());
    }
    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ExceptionResponse handleEnrollmentNotFoundException(Exception ex, HttpServletRequest request){
        return new ExceptionResponse(Instant.now(), 404, request.getRequestURI(), "Enrollment Not Found", ex.getMessage());
    }
    @ExceptionHandler({MongoTimeoutException.class, DataAccessResourceFailureException.class})
    public ExceptionResponse handleMongoDisconnected(Exception ex) {
        return new ExceptionResponse(Instant.now(), 404, null, "MongoDB disconnected", ex.getMessage());
    }
}
