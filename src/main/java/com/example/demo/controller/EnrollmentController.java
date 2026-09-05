package com.example.demo.controller;

import com.example.demo.model.Enrollment;
import com.example.demo.service.EnrollmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@Tag(name = "Enrollment", description = "Enrollment-related endpoints")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new enrollment")
    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return service.createEnrollment(enrollment);
    }

    @Operation(summary = "Get all enrollments")
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return service.getAllEnrollments();
    }

    @Operation(summary = "Get an enrollment by id")
    @GetMapping("/{id}")
    public Enrollment getEnrollment(@PathVariable("id") String id) {
        return service.getEnrollmentById(id);
    }

    @Operation(summary = "Get enrollments by student id")
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable("studentId") String studentId) {
        return service.getEnrollmentsByStudentId(studentId);
    }

    @Operation(summary = "Get enrollments by subject id")
    @GetMapping("/subject/{subjectId}")
    public List<Enrollment> getEnrollmentsBySubject(@PathVariable("subjectId") String subjectId) {
        return service.getEnrollmentsBySubjectId(subjectId);
    }

    @Operation(summary = "Update an enrollment")
    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable("id") String id, @RequestBody Enrollment enrollmentDetails) {
        return service.updateEnrollment(id, enrollmentDetails);
    }

    @Operation(summary = "Delete an enrollment")
    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable("id") String id) {
        service.deleteEnrollment(id);
    }
}
