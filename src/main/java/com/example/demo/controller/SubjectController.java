package com.example.demo.controller;

import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@Tag(name = "Subject", description = "Subject-related endpoints")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new subject")
    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return service.createSubject(subject);
    }

    @Operation(summary = "Get a subject by id")
    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable("id") String id) {
        return service.getSubjectById(id);
    }

    @Operation(summary = "Get all subjects")
    @GetMapping
    public List<Subject> getAllSubjects() {
        return service.getAllSubjects();
    }

    @Operation(summary = "Update a subject")
    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable("id") String id, @RequestBody Subject subjectDetails) {
        return service.updateSubject(id, subjectDetails);
    }

    @Operation(summary = "Delete a subject")
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable("id") String id) {
        service.deleteSubject(id);
    }
}
