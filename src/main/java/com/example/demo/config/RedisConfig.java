package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.demo.model.Student;
import com.example.demo.model.Subject;

@Configuration
public class RedisConfig {

    @Bean(name = "studentRedisTemplate")
    public RedisTemplate<String, Student> studentRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Student> template = new RedisTemplate<>();
        configureTemplate(template, connectionFactory);
        return template;
    }

    @Bean(name = "subjectRedisTemplate")
    public RedisTemplate<String, Subject> subjectRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Subject> template = new RedisTemplate<>();
        configureTemplate(template, connectionFactory);
        return template;
    }

    private <T> void configureTemplate(RedisTemplate<String, T> template, RedisConnectionFactory connectionFactory) {
        template.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setValueSerializer(serializer);
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
    }
}
