package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.health.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private HealthEndpoint healthEndpoint;

	@Test
	void contextLoads() {
	}

	@Test
	void healthEndpointIsAvailable() {
		assertThat(healthEndpoint).isNotNull();
		assertThat(healthEndpoint.health().getStatus()).isNotNull();
	}

}
