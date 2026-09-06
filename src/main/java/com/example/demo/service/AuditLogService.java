package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.model.AuditLog;
import com.example.demo.model.AuditLogPrimaryKey;
import com.example.demo.repository.AuditLogRepository;
import java.util.List;
import java.time.Instant;

@Service
public class AuditLogService {

    private final AuditLogRepository repository;

    public AuditLogService(AuditLogRepository repository) {
        this.repository = repository;
    }

    public AuditLog logEvent(String entityType, String entityId, String action, String details, String source) {
        AuditLog auditLog = new AuditLog();
        auditLog.setKey(new AuditLogPrimaryKey(entityType, entityId, Instant.now()));
        auditLog.setAction(action);
        auditLog.setDetails(details);
        auditLog.setSource(source);
        return repository.save(auditLog);
    }

    public List<AuditLog> getAuditLogsByEntity(String entityType, String entityId) {
        return repository.findByKeyEntityTypeAndKeyEntityId(entityType, entityId);
    }

    public List<AuditLog> getAuditLogsByTimeRange(String entityType, Instant start, Instant end) {
        return repository.findByKeyEntityTypeAndKeyTimestampBetween(entityType, start, end);
    }

    public List<AuditLog> getAllAuditLogs() {
        return repository.findAll();
    }
}