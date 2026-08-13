package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;
import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository repository;

    public SubjectService(SubjectRepository repository) {
        this.repository = repository;
    }

    public Subject createSubject(Subject subject) {
        return repository.save(subject);
    }

    public Subject getSubjectById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
    }

    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    public Subject updateSubject(String id, Subject subjectDetails) {
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setName(subjectDetails.getName());
        subject.setDescription(subjectDetails.getDescription());
        subject.setNumberOfCredit(subjectDetails.getNumberOfCredit());

        return repository.save(subject);
    }

    public void deleteSubject(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Subject not found");
        }
        repository.deleteById(id);
    }
}
