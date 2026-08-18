package com.example.demo.service;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.config.KafkaConfig;
import com.example.demo.exception.custom.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final RedisTemplate<String, Student> redisTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public StudentService(StudentRepository repository,
                          @Qualifier("studentRedisTemplate") RedisTemplate<String, Student> redisTemplate,
                          KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.redisTemplate = redisTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Student createStudent(Student student) {
        // 1. Save to MongoDB
        Student saved = repository.save(student);

        // 2. Publish event to Kafka
        kafkaTemplate.send(KafkaConfig.STUDENT_EVENT_TOPIC, saved.getId(), "Created student with ID: " + saved.getId() + " and name " + saved.getName());

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
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));

        // 3. Store in Redis for 10 minutes
        redisTemplate.opsForValue().set(cacheKey, student, Duration.ofMinutes(10));

        return student;
    }

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student updateStudent(String id, Student studentDetails) {
        String cacheKey = "student:" + id;

        // 1. Find existing student
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));

        // 2. Update fields
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());

        // 3. Save to MongoDB
        Student updated = repository.save(student);

        // 4. Update Redis Cache
        redisTemplate.opsForValue().set(cacheKey, updated, Duration.ofMinutes(10));

        // 5. Publish event to Kafka
        kafkaTemplate.send(KafkaConfig.STUDENT_EVENT_TOPIC, updated.getId(), "Updated student with ID: " + updated.getId() + " and name " + updated.getName());

        return updated;
    }

    public void deleteStudent(String id) {
        String cacheKey = "student:" + id;

        // 1. Check if student exists
        Student student = repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id " + id + " not found"));

        // 2. Delete from MongoDB
        repository.deleteById(id);

        // 3. Remove from Redis Cache
        redisTemplate.delete(cacheKey);

        // 4. Publish event to Kafka
        kafkaTemplate.send(KafkaConfig.STUDENT_EVENT_TOPIC, id, "Deleted student with ID: " + id + " and name " + student.getName());
    }
}