package com.example.weather;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class BaseSpringBootTestWithContainers {
	
	@Container
	static PostgreSQLContainer<?> db = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15.2"))
			.withUsername("postgres").withPassword("postgres").withDatabaseName("test-db").withReuse(true);

	@DynamicPropertySource
	static void postgresqlProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", db::getJdbcUrl);
		registry.add("spring.datasource.password", db::getPassword);
		registry.add("spring.datasource.username", db::getUsername);
	}

}
