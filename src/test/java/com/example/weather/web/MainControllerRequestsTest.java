package com.example.weather.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.weather.BaseSpringBootTestWithContainers;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MainControllerRequestsTest extends BaseSpringBootTestWithContainers {

	@LocalServerPort
	private int port;

	private String host = "http://localhost";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void mainPingShouldReturnPong() {
		ResponseEntity<String> response = this.restTemplate.getForEntity(host + ":" + port + "/", String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertThat(response.getBody().equals("pong"));
	}

	@Test
	void mainPingShouldReturnPongPlusParameter() {
		ResponseEntity<String> response = this.restTemplate.getForEntity(host + ":" + port + "/message", String.class);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), "pong message");
	}
}
