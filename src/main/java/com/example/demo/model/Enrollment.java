package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;


@Document(collection = "enrollments")
@Getter @Setter
public class Enrollment implements Serializable {
    @Id
    private String id;
    private String studentId;
    private String subjectId;

    private String academicYear; // example: 2026-1
    private Integer midtermExamScore;
    private Integer finalTermExamScore;
}
