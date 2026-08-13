package com.example.demo.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import java.time.Duration;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final RedisTemplate<String, Student> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StudentService(StudentRepository repository, 
                          RedisTemplate<String, Student> redisTemplate, 
                          KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Student createStudent(Student student) {
        // 1. Save to MongoDB
        Student saved = repository.save(student);

        // 2. Publish event to Kafka
        kafkaTemplate.send("student-events", saved.getId(), "Created student with ID: " + saved.getId() + " and name " + saved.getName());

        return saved;
    }

    public Student getStudentById(String id) {
        String cacheKey = "student:" + id;

        // 1. Check Redis Cache
        Student cachedStudent = (Student) redisTemplate.opsForValue().get(cacheKey);
        if (cachedStudent != null) {
            System.out.println("Fetched from Redis Cache");
            return cachedStudent;
        }

        // 2. Fallback to MongoDB
        System.out.println("Fetched from MongoDB");
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        // 3. Store in Redis for 10 minutes
        redisTemplate.opsForValue().set(cacheKey, student, Duration.ofMinutes(10));

        return student;
    }
}