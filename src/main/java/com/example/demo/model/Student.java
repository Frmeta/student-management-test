package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "students")
@Getter @Setter
public class Student implements Serializable {
    @Id
    private String id;
    private String name;
    private String email;

    public Student() {}

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }
}