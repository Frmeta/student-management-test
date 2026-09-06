package com.example.demo.model;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import java.io.Serializable;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@PrimaryKeyClass
@Getter @Setter
public class AuditLogPrimaryKey implements Serializable {

    @PrimaryKeyColumn(name = "entity_type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String entityType;

    @PrimaryKeyColumn(name = "entity_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String entityId;

    @PrimaryKeyColumn(name = "timestamp", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Instant timestamp;

    public AuditLogPrimaryKey() {
    }

    public AuditLogPrimaryKey(String entityType, String entityId, Instant timestamp) {
        this.entityType = entityType;
        this.entityId = entityId;
        this.timestamp = timestamp;
    }
}