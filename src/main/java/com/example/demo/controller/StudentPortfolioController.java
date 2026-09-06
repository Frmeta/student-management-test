package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.model.StudentPortfolio;
import com.example.demo.service.StudentPortfolioService;

@RestController
@RequestMapping("/portfolios")
@Tag(name = "Student Portfolio", description = "Student portfolio-related endpoints")
public class StudentPortfolioController {

    private final StudentPortfolioService service;

    public StudentPortfolioController(StudentPortfolioService service) {
        this.service = service;
    }

    @Operation(summary = "Create a new portfolio")
    @PostMapping
    public StudentPortfolio createPortfolio(@RequestBody StudentPortfolio portfolio) {
        return service.createPortfolio(portfolio);
    }

    @Operation(summary = "Get portfolio by id")
    @GetMapping("/{id}")
    public StudentPortfolio getPortfolio(@PathVariable("id") String id) {
        return service.getPortfolioById(id);
    }

    @Operation(summary = "Get portfolio by student id")
    @GetMapping("/student/{studentId}")
    public StudentPortfolio getPortfolioByStudent(@PathVariable("studentId") String studentId) {
        return service.getPortfolioByStudentId(studentId);
    }

    @Operation(summary = "Get all portfolios")
    @GetMapping
    public List<StudentPortfolio> getAllPortfolios() {
        return service.getAllPortfolios();
    }

    @Operation(summary = "Update a portfolio")
    @PutMapping("/{id}")
    public StudentPortfolio updatePortfolio(@PathVariable("id") String id, @RequestBody StudentPortfolio portfolioDetails) {
        return service.updatePortfolio(id, portfolioDetails);
    }

    @Operation(summary = "Delete a portfolio")
    @DeleteMapping("/{id}")
    public void deletePortfolio(@PathVariable("id") String id) {
        service.deletePortfolio(id);
    }
}
