package com.example.weather.controller;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping(value = {"/ping/{message}", "/ping"})
    @Operation(summary = "Ping. Returns pong.")
    @ApiResponse(responseCode = "200", description = "Expected pong response", content = @Content)
    public String ping(@PathVariable(required = false) String message) {
        return "pong " + Optional.ofNullable(message).orElse("");
    }
}
