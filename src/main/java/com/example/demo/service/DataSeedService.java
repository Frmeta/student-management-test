package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;
import com.example.demo.model.Enrollment;
import com.example.demo.model.StudentPortfolio;

import java.util.Arrays;
import java.util.List;

@Service
public class DataSeedService {

    private final StudentService studentService;
    private final SubjectService subjectService;
    private final EnrollmentService enrollmentService;
    private final StudentPortfolioService portfolioService;

    public DataSeedService(StudentService studentService,
                          SubjectService subjectService,
                          EnrollmentService enrollmentService,
                          StudentPortfolioService portfolioService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.enrollmentService = enrollmentService;
        this.portfolioService = portfolioService;
    }

    public String seedAllData() {
        Student student1 = createStudent("John Doe", "john.doe@example.com");
        Student student2 = createStudent("Jane Smith", "jane.smith@example.com");
        Student student3 = createStudent("Bob Johnson", "bob.johnson@example.com");

        Subject subject1 = createSubject("Mathematics", "Advanced calculus and linear algebra", "4");
        Subject subject2 = createSubject("Computer Science", "Data structures and algorithms", "3");
        Subject subject3 = createSubject("Physics", "Mechanics and thermodynamics", "3");

        createEnrollment(student1.getId(), subject1.getId(), "2026-1", 85, 90);
        createEnrollment(student1.getId(), subject2.getId(), "2026-1", 78, 82);
        createEnrollment(student2.getId(), subject1.getId(), "2026-1", 92, 88);
        createEnrollment(student2.getId(), subject3.getId(), "2026-1", 75, 80);
        createEnrollment(student3.getId(), subject2.getId(), "2026-1", 88, 85);

        createPortfolio(student1.getId(), "Computer Science student",
                Arrays.asList("Java", "Python", "SQL"),
                Arrays.asList("Dean's List 2025"), "3.8", "2027");
        createPortfolio(student2.getId(), "Mathematics enthusiast",
                Arrays.asList("Calculus", "Statistics", "R"),
                Arrays.asList("Math Competition Winner"), "3.9", "2027");
        createPortfolio(student3.getId(), "Aspiring physicist",
                Arrays.asList("Physics", "MATLAB", "Lab Research"),
                Arrays.asList("Research Assistant"), "3.7", "2028");

        return "Sample data seeded successfully! 3 students, 3 subjects, 5 enrollments, 3 portfolios.";
    }

    private Student createStudent(String name, String email) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        return studentService.createStudent(student);
    }

    private Subject createSubject(String name, String description, String numberOfCredit) {
        Subject subject = new Subject();
        subject.setName(name);
        subject.setDescription(description);
        subject.setNumberOfCredit(numberOfCredit);
        return subjectService.createSubject(subject);
    }

    private Enrollment createEnrollment(String studentId, String subjectId, String academicYear, Integer midterm, Integer finalTerm) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setSubjectId(subjectId);
        enrollment.setAcademicYear(academicYear);
        enrollment.setMidtermExamScore(midterm);
        enrollment.setFinalTermExamScore(finalTerm);
        return enrollmentService.createEnrollment(enrollment);
    }

    private void createPortfolio(String studentId, String bio, List<String> skills, List<String> achievements, String gpa, String expectedGrad) {
        StudentPortfolio portfolio = new StudentPortfolio();
        portfolio.setStudentId(studentId);
        portfolio.setBio(bio);
        portfolio.setSkills(skills);
        portfolio.setAchievements(achievements);
        portfolio.setGpa(gpa);
        portfolio.setExpectedGraduationYear(expectedGrad);
        portfolioService.createPortfolio(portfolio);
    }
}