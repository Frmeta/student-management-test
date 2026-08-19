package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.example.demo.config.RedisConfig;
import com.example.demo.model.Student;

class RedisConfigTest {

    @Test
    void studentRedisTemplate_usesJsonSerializer() {
        RedisConfig config = new RedisConfig();
        RedisConnectionFactory connectionFactory = mock(RedisConnectionFactory.class);

        RedisTemplate<String, Student> template = config.studentRedisTemplate(connectionFactory);

        assertThat(template.getValueSerializer()).isInstanceOf(JacksonJsonRedisSerializer.class);

        Student student = new Student();
        student.setId("s-1");
        student.setName("Alice");
        student.setEmail("alice@example.com");

        @SuppressWarnings("unchecked")
        RedisSerializer<Object> serializer = (RedisSerializer<Object>) template.getValueSerializer();

        byte[] serialized = serializer.serialize(student);
        Student deserialized = (Student) serializer.deserialize(serialized);

        assertThat(deserialized).isNotNull();
        assertThat(deserialized.getId()).isEqualTo("s-1");
        assertThat(deserialized.getName()).isEqualTo("Alice");
    }
}
