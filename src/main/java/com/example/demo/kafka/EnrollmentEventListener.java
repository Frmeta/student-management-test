package com.example.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.demo.config.KafkaConfig;
import com.example.demo.service.AuditLogService;

@Component
public class EnrollmentEventListener {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentEventListener.class);
    private final AuditLogService auditLogService;

    public EnrollmentEventListener(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @KafkaListener(topics = KafkaConfig.ENROLLMENT_EVENT_TOPIC, groupId = "enrollment-group")
    public void handleEnrollmentEvent(ConsumerRecord<String, String> record) {
        try {
            String key = record.key();
            String message = record.value();

            String action = message.contains("Created") ? "CREATE" :
                            message.contains("Updated") ? "UPDATE" : "DELETE";

            auditLogService.logEvent("ENROLLMENT", key, action, message, "KAFKA");

            logger.info("=== Enrollment Event Processed ===");
            logger.info("Key: {}", key);
            logger.info("Message: {}", message);
            logger.info("Audit log saved to ScyllaDB");

        } catch (Exception e) {
            logger.error("Error processing enrollment event: {}", record.value(), e);
            throw e;
        }
    }
}