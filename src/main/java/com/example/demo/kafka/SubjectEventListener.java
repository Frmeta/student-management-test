package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.config.KafkaConfig;

@Component
public class SubjectEventListener {
    
    private static final Logger logger = LoggerFactory.getLogger(SubjectEventListener.class);

    @KafkaListener(topics = KafkaConfig.SUBJECT_EVENT_TOPIC, groupId = "subject-group")
    public void handleSubjectEvent(ConsumerRecord<String, String> record) {
        try {
            String key = record.key();
            String message = record.value();
            long timestamp = record.timestamp();
            
            logger.info("=== Subject Event Consumed ===");
            logger.info("Topic: {}", record.topic());
            logger.info("Partition: {}", record.partition());
            logger.info("Offset: {}", record.offset());
            logger.info("Key: {}", key);
            logger.info("Message: {}", message);
            logger.info("Timestamp: {}", timestamp);
            logger.info("================================");
            
            // TODO: Implement business logic here
            // Examples:
            // - Persist event to audit log database
            // - Notify subscribed services about subject changes
            // - Update search indexes
            // - Trigger validations on related enrollments
            
        } catch (Exception e) {
            logger.error("Error processing subject event: {}", record.value(), e);
            throw e;
        }
    }
}
