package com.example.demo.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import java.io.Serializable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Table("audit_logs")
@Getter @Setter
public class AuditLog implements Serializable {

    @PrimaryKey
    private AuditLogPrimaryKey key;

    private UUID id;

    private String action;

    private String details;

    private String source;

    public AuditLog() {
        this.id = UUID.randomUUID();
    }

    public String getEntityType() {
        return key != null ? key.getEntityType() : null;
    }

    public String getEntityId() {
        return key != null ? key.getEntityId() : null;
    }

    public java.time.Instant getTimestamp() {
        return key != null ? key.getTimestamp() : null;
    }
}