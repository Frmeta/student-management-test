package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Enrollment;
import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    List<Enrollment> findByStudentId(String studentId);
    List<Enrollment> findBySubjectId(String subjectId);
}