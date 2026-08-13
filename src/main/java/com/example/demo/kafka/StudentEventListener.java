package com.example.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @KafkaListener(topics = "student-events", groupId = "student-group")
    public void handleStudentCreated(String message) {
        System.out.println("Kafka Event Received: " + message);
    }
}