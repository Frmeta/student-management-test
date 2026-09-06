package com.example.demo.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Setter;
import lombok.Getter;

@Entity
@Table(name = "subjects")
@Getter @Setter
public class Subject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    private String description;
    
    @Column(name = "number_of_credit")
    private String numberOfCredit;
}
