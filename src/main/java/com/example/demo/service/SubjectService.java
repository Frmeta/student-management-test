package com.example.demo.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.KafkaConfig;
import com.example.demo.exception.custom.SubjectNotFoundException;
import com.example.demo.model.Subject;
import com.example.demo.repository.SubjectRepository;

@Service
public class SubjectService {

    private final SubjectRepository repository;
    private final RedisTemplate<String, Subject> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SubjectService(SubjectRepository repository,
                          @Qualifier("subjectRedisTemplate") RedisTemplate<String, Subject> redisTemplate,
                          KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Subject createSubject(Subject subject) {
        Subject saved = repository.save(subject);
        kafkaTemplate.send(KafkaConfig.SUBJECT_EVENT_TOPIC, saved.getId(), "Created subject with ID: " + saved.getId() + " and name " + saved.getName());
        return saved;
    }

    public Subject getSubjectById(String id) {
        String cacheKey = "subject:" + id;

        // 1. Check Redis Cache
        Subject cachedSubject = (Subject) redisTemplate.opsForValue().get(cacheKey);
        if (cachedSubject != null) {
            System.out.println("Fetched from Redis Cache");
            return cachedSubject;
        }
        // 2. Fallback to MongoDB
        System.out.println("Fetched from MongoDB");
        Subject subject = repository.findById(id).orElseThrow(() -> new SubjectNotFoundException("Subject with id " + id + " not found"));

        // 3. Store in Redis for 10 minutes
        redisTemplate.opsForValue().set(cacheKey, subject, Duration.ofMinutes(10));

        return subject;
    }

    public List<Subject> getAllSubjects() {
        return repository.findAll();
    }

    public Subject updateSubject(String id, Subject subjectDetails) {
        String cacheKey = "subject:" + id;
        
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with id " + id + " not found"));

        subject.setName(subjectDetails.getName());
        subject.setDescription(subjectDetails.getDescription());
        subject.setNumberOfCredit(subjectDetails.getNumberOfCredit());

        Subject updated = repository.save(subject);
        
        // Update Redis Cache
        redisTemplate.opsForValue().set(cacheKey, updated, Duration.ofMinutes(10));
        
        // Publish event to Kafka
        kafkaTemplate.send(KafkaConfig.SUBJECT_EVENT_TOPIC, updated.getId(), "Updated subject with ID: " + updated.getId() + " and name " + updated.getName());

        return updated;
    }

    public void deleteSubject(String id) {
        String cacheKey = "subject:" + id;
        
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new SubjectNotFoundException("Subject with id " + id + " not found"));
        
        repository.deleteById(id);
        
        // Remove from Redis Cache
        redisTemplate.delete(cacheKey);
        
        // Publish event to Kafka
        kafkaTemplate.send(KafkaConfig.SUBJECT_EVENT_TOPIC, id, "Deleted subject with ID: " + id + " and name " + subject.getName());
    }
}
