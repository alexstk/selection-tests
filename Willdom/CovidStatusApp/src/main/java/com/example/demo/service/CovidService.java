package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Covid;

public interface CovidService {

	Covid getById(Long id);

	List<Covid> getTop5(String by);

	Integer getTotalBy(String by);

	void create(Covid covid);

}
