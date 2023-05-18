package com.example.weather.api;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class MainController {
	
	@GetMapping(value = {"/{message}", "/"})
	public String ping(@PathVariable(required = false) String message) {
		return "pong " + Optional.ofNullable(message).orElse("");
	}
}