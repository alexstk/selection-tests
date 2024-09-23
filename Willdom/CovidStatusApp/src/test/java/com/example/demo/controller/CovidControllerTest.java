package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Covid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
	scripts = "classpath:data.sql")
public class CovidControllerTest {
	ObjectMapper om = new ObjectMapper();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CrudRepository repository;

	@Test
	public void testCreation() throws Exception {
//		Covid expectedRecord = Covid.builder().country("Test Country").build();
//		Covid actualRecord = om.readValue(mockMvc.perform(get("/covid/1")
//				.contentType("application/json")
//				.content(om.writeValueAsString(expectedRecord)))
//				.andDo(print())
//				.andExpect(status().isCreated())
//				.andReturn().getResponse().getContentAsString(), Covid.class);

		this.mockMvc
				.perform(get("/api/covid/10"))
				.andDo(print())
				.andExpect(status().is(404));
//				.andExpect(content().string(containsString("country1")));

	}

}
