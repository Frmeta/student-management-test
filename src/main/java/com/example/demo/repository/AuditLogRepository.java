package com.example.demo.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import com.example.demo.model.AuditLog;
import com.example.demo.model.AuditLogPrimaryKey;
import java.time.Instant;
import java.util.List;

public interface AuditLogRepository extends CassandraRepository<AuditLog, AuditLogPrimaryKey> {
    List<AuditLog> findByKeyEntityTypeAndKeyEntityId(String entityType, String entityId);

    @Query("SELECT * FROM audit_logs WHERE entity_type = ?0 AND timestamp >= ?1 AND timestamp <= ?2 ALLOW FILTERING")
    List<AuditLog> findByKeyEntityTypeAndKeyTimestampBetween(String entityType, Instant start, Instant end);
}