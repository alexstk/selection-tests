package com.example.demo.service.imp;

import com.example.demo.exception.CovidServiceException;
import com.example.demo.model.Covid;
import com.example.demo.repository.CovidRepository;
import com.example.demo.service.CovidService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CovidServiceImp implements CovidService {
	
	@Autowired
	private CovidRepository covidRepository;

	@Override
	public Covid getById(Long id) {
		return covidRepository.findById(id)
				.orElseThrow(() -> new CovidServiceException("Not found"));
	}

	@Override
	public List<Covid> getTop5(String by) {
		Sort sort = Sort.by(by).descending();
		Pageable pageable = PageRequest.of(0, 5, sort);
		return covidRepository.findAll(pageable).getContent();
	}

	@Override
	public Integer getTotalBy(String by) {
		return covidRepository.totalBy(by);
	}

	@Override
	public void create(Covid covid) {
		covidRepository.save(covid);
	}

}
