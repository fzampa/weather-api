package com.example.weather.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

	public String ping(@PathVariable(required = false) String message) {
		return "pong " + Optional.ofNullable(message).orElse("");
	}
}
