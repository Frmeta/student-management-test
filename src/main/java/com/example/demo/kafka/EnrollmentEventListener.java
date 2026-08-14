package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.config.KafkaConfig;

@Component
public class EnrollmentEventListener {
    
    private static final Logger logger = LoggerFactory.getLogger(EnrollmentEventListener.class);

    @KafkaListener(topics = KafkaConfig.ENROLLMENT_EVENT_TOPIC, groupId = "enrollment-group")
    public void handleEnrollmentEvent(ConsumerRecord<String, String> record) {
        try {
            String key = record.key();
            String message = record.value();
            long timestamp = record.timestamp();
            
            logger.info("=== Enrollment Event Consumed ===");
            logger.info("Topic: {}", record.topic());
            logger.info("Partition: {}", record.partition());
            logger.info("Offset: {}", record.offset());
            logger.info("Key: {}", key);
            logger.info("Message: {}", message);
            logger.info("Timestamp: {}", timestamp);
            logger.info("==================================");
            
            // TODO: Implement business logic here
            // Examples:
            // - Persist event to audit log database
            // - Trigger notifications (email student about enrollment)
            // - Update external systems
            // - Validate enrollment constraints
            
        } catch (Exception e) {
            logger.error("Error processing enrollment event: {}", record.value(), e);
            throw e;
        }
    }
}
