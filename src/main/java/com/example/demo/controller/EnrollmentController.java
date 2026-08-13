package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.model.Enrollment;
import com.example.demo.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService service;

    public EnrollmentController(EnrollmentService service) {
        this.service = service;
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return service.createEnrollment(enrollment);
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return service.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public Enrollment getEnrollment(@PathVariable String id) {
        return service.getEnrollmentById(id);
    }

    @GetMapping("/student/{studentId}")
    public List<Enrollment> getEnrollmentsByStudent(@PathVariable String studentId) {
        return service.getEnrollmentsByStudentId(studentId);
    }

    @GetMapping("/subject/{subjectId}")
    public List<Enrollment> getEnrollmentsBySubject(@PathVariable String subjectId) {
        return service.getEnrollmentsBySubjectId(subjectId);
    }
    @PutMapping("/{id}")
    public Enrollment updateEnrollment(@PathVariable String id, @RequestBody Enrollment enrollmentDetails) {
        return service.updateEnrollment(id, enrollmentDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollment(@PathVariable String id) {
        service.deleteEnrollment(id);
    }
}
