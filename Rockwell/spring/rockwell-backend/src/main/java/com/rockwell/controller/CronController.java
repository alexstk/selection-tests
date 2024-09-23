package com.rockwell.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rockwell.domain.CronConfig;
import com.rockwell.service.CronService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200/" })
@RestController
@RequestMapping("/api")
public class CronController {

	@Autowired
	private CronService cronService;

	@PostMapping("/configure-cron")
	public ResponseEntity<?> configureCron(@Valid @RequestBody CronConfig cronConfig, BindingResult result) {
		if (result.hasErrors()) {
			return createErrorResponse(result);
		}
			
		cronService.processExecution(cronConfig);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private ResponseEntity<?> createErrorResponse(BindingResult result) {
		List<String> errors = result.getFieldErrors()
				.stream()
				.map(e -> "Field '" + e.getField() + "' " + e.getDefaultMessage())
				.collect(Collectors.toList());
		
		return new ResponseEntity<Map<String, List<String>>>(Map.of("errors", errors), HttpStatus.BAD_REQUEST);
	}

}
