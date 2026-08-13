package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) {
        return service.createSubject(subject);
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable String id) {
        return service.getSubjectById(id);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return service.getAllSubjects();
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable String id, @RequestBody Subject subjectDetails) {
        return service.updateSubject(id, subjectDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable String id) {
        service.deleteSubject(id);
    }
}
