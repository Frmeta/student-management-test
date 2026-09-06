package com.example.demo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.exception.custom.PortfolioNotFoundException;
import com.example.demo.model.StudentPortfolio;
import com.example.demo.repository.StudentPortfolioRepository;

@Service
public class StudentPortfolioService {

    private final StudentPortfolioRepository repository;

    public StudentPortfolioService(StudentPortfolioRepository repository) {
        this.repository = repository;
    }

    public StudentPortfolio createPortfolio(StudentPortfolio portfolio) {
        return repository.save(portfolio);
    }

    public StudentPortfolio getPortfolioById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio with id " + id + " not found"));
    }

    public StudentPortfolio getPortfolioByStudentId(String studentId) {
        return repository.findByStudentId(studentId)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio for student " + studentId + " not found"));
    }

    public List<StudentPortfolio> getAllPortfolios() {
        return repository.findAll();
    }

    public StudentPortfolio updatePortfolio(String id, StudentPortfolio portfolioDetails) {
        StudentPortfolio portfolio = repository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio with id " + id + " not found"));
        portfolio.setBio(portfolioDetails.getBio());
        portfolio.setSkills(portfolioDetails.getSkills());
        portfolio.setAchievements(portfolioDetails.getAchievements());
        portfolio.setGpa(portfolioDetails.getGpa());
        portfolio.setExpectedGraduationYear(portfolioDetails.getExpectedGraduationYear());
        return repository.save(portfolio);
    }

    public void deletePortfolio(String id) {
        repository.findById(id)
                .orElseThrow(() -> new PortfolioNotFoundException("Portfolio with id " + id + " not found"));
        repository.deleteById(id);
    }
}
