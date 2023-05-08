package com.example.weather.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MainControllerRequestsTest {

	@LocalServerPort
	private int port;

	private String host = "http://localhost";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void mainPingShouldReturnPong() {
		assertThat(this.restTemplate.getForObject(host + ":" + port + "/", String.class).equals("pong"));
	}

	@Test
	void mainPingShouldReturnPongPlusParameter() {
		assertThat(this.restTemplate.getForObject(host + ":" + port + "/message", String.class).equals("pong message"));
	}
}
