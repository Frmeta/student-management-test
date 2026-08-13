package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

import com.example.demo.model.Enrollment;

public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByStudentId(String studentId);
    List<Enrollment> findBySubjectId(String subjectId);
}