package com.example.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        String uri = System.getProperty(
            "spring.data.mongodb.uri",
            System.getenv().getOrDefault("MONGODB_URI", "mongodb://localhost:27017")
        );

        MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(uri))

            .applyToSocketSettings(builder -> builder
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS))

            .applyToClusterSettings(builder ->
                builder.serverSelectionTimeout(3, TimeUnit.SECONDS))

            .applyToConnectionPoolSettings(builder -> builder
                .maxSize(100)
                .minSize(10)
                .maxWaitTime(2, TimeUnit.SECONDS)
                .maxConnectionIdleTime(60, TimeUnit.SECONDS))
                
            .build();

        return MongoClients.create(settings);
    }
}