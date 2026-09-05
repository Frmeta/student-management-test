package com.example.demo.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/students")
@Tag(name = "Student-related endpoints")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new student")
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.createStudent(student);
    }

    @Operation(summary = "Get student by id")
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") String id) {
        return service.getStudentById(id);
    }

    @Operation(summary = "Get all students")
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAllStudents();
    }

    @Operation(summary = "Update a student")
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable("id") String id, @RequestBody Student studentDetails) {
        return service.updateStudent(id, studentDetails);
    }

    @Operation(summary = "Delete a student")
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        service.deleteStudent(id);
    }
}