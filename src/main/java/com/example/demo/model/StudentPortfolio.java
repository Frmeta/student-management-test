package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "student_portfolios")
@Getter @Setter
public class StudentPortfolio implements Serializable {
    @Id
    private String id;
    
    private String studentId;
    
    private String bio;
    
    private List<String> skills;
    
    private List<String> achievements;
    
    private String gpa;
    
    private String expectedGraduationYear;
}
