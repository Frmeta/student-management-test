package com.example.demo.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;

@Document(collection = "subjects")
@Getter @Setter
public class Subject implements Serializable {
    @Id
    private String id;
    private String name;
    private String description;
    private String numberOfCredit;
}
