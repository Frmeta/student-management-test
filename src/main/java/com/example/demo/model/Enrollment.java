package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enrollments")
@Getter @Setter
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(name = "student_id", nullable = false)
    private String studentId;
    
    @Column(name = "subject_id", nullable = false)
    private String subjectId;
    
    @Column(name = "academic_year")
    private String academicYear;
    
    @Column(name = "midterm_exam_score")
    private Integer midtermExamScore;
    
    @Column(name = "final_term_exam_score")
    private Integer finalTermExamScore;
}
