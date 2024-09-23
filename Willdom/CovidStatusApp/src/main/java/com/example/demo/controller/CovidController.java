package com.example.demo.controller;

import java.util.List;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Covid;
import com.example.demo.service.CovidService;

@Slf4j
@RestController
@RequestMapping("/api")
public class CovidController {

	@Autowired
	private CovidService covidService;
	
	@GetMapping("/covid/{id}")
	public ResponseEntity<Covid> getById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(covidService.getById(id), HttpStatus.valueOf(200));
	}
	
	@GetMapping("/covid/top5")
	public ResponseEntity<List<Covid>> getTop5(@RequestParam String by) {
		log.info("By: {}", by);
		return new ResponseEntity<>(covidService.getTop5(by), HttpStatus.OK);
	}
	
	@GetMapping("/covid/total")
	public ResponseEntity<Integer> getTotalBy(@RequestParam("by") String by) {
		return new ResponseEntity<>(covidService.getTotalBy(by), HttpStatus.OK);
	}

	@PostMapping("/covid")
	public ResponseEntity<String> create(@RequestBody Covid covid) {
		covidService.create(covid);
		return new ResponseEntity<>("Created", HttpStatus.CREATED);
	}
	
}
