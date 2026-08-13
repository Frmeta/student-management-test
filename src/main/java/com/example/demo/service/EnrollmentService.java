package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Enrollment;
import com.example.demo.repository.EnrollmentRepository;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(EnrollmentRepository repository) {
        this.repository = repository;
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return repository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return repository.findAll();
    }

    public Enrollment getEnrollmentById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
    }

    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId);
    }

    public Enrollment updateEnrollment(String id, Enrollment enrollmentDetails) {
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        enrollment.setStudentId(enrollmentDetails.getStudentId());
        enrollment.setSubjectId(enrollmentDetails.getSubjectId());
        enrollment.setAcademicYear(enrollmentDetails.getAcademicYear());
        enrollment.setMidtermExamScore(enrollmentDetails.getMidtermExamScore());
        enrollment.setFinalTermExamScore(enrollmentDetails.getFinalTermExamScore());

        return repository.save(enrollment);
    }

    public void deleteEnrollment(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Enrollment not found");
        }
        repository.deleteById(id);
    }
}
