package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.model.StudentPortfolio;
import java.util.Optional;

public interface StudentPortfolioRepository extends MongoRepository<StudentPortfolio, String> {
    Optional<StudentPortfolio> findByStudentId(String studentId);
}
