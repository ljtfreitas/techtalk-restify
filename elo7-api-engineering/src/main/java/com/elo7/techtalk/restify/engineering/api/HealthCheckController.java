package com.elo7.techtalk.restify.engineering.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

	@RequestMapping("/health")
	public ResponseEntity<String> health() {
		return ResponseEntity.ok("OK");
	}
}
