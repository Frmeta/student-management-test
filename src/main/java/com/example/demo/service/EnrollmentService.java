package com.example.demo.service;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.KafkaConfig;
import com.example.demo.exception.custom.EnrollmentNotFoundException;
import com.example.demo.model.Enrollment;
import com.example.demo.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public EnrollmentService(EnrollmentRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        Enrollment saved = repository.save(enrollment);
        kafkaTemplate.send(KafkaConfig.ENROLLMENT_EVENT_TOPIC, saved.getId(), "Created enrollment with ID: " + saved.getId() + " for Student: " + saved.getStudentId() + " Subject: " + saved.getSubjectId());
        return saved;
    }

    public List<Enrollment> getAllEnrollments() {
        return repository.findAll();
    }

    public Enrollment getEnrollmentById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with id " + id +" not found"));
    }

    public List<Enrollment> getEnrollmentsByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsBySubjectId(String subjectId) {
        return repository.findBySubjectId(subjectId);
    }

    public Enrollment updateEnrollment(String id, Enrollment enrollmentDetails) {
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with id " + id + " not found"));

        enrollment.setStudentId(enrollmentDetails.getStudentId());
        enrollment.setSubjectId(enrollmentDetails.getSubjectId());
        enrollment.setAcademicYear(enrollmentDetails.getAcademicYear());
        enrollment.setMidtermExamScore(enrollmentDetails.getMidtermExamScore());
        enrollment.setFinalTermExamScore(enrollmentDetails.getFinalTermExamScore());

        Enrollment updated = repository.save(enrollment);
        kafkaTemplate.send(KafkaConfig.ENROLLMENT_EVENT_TOPIC, updated.getId(), "Updated enrollment with ID: " + updated.getId() + " for Student: " + updated.getStudentId() + " Subject: " + updated.getSubjectId());
        return updated;
    }

    public void deleteEnrollment(String id) {
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with id " + id + " not found"));
        repository.deleteById(id);
        kafkaTemplate.send(KafkaConfig.ENROLLMENT_EVENT_TOPIC, id, "Deleted enrollment with ID: " + id + " for Student: " + enrollment.getStudentId() + " Subject: " + enrollment.getSubjectId());
    }
}
