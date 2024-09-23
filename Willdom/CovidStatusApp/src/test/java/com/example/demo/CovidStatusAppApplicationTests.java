package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.model.Covid;
import com.example.demo.repository.CovidRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import jdk.jfr.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;



@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = CovidStatusAppApplication.class)
@AutoConfigureMockMvc
//@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class CovidStatusAppApplicationTests {

	@Autowired
	private MockMvc mvc;

	private ObjectMapper om = new ObjectMapper();

//	@Autowired
//	private CovidRepository covidRepository;

	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception {
		mvc.perform(get("/api/covid/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.country", is("country1")));
	}

	@Test
	public void givenEmployees_whenGetTop5_thenStatus200()	throws Exception {
		JsonNode top5 = om.readValue(
			mvc.perform(get("/api/covid/top5?by=active").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andDo(print())
					.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
					.andExpect(jsonPath("$.[0].country", is("country7")))
					.andExpect(jsonPath("$.[0].active", is(670)))
					.andReturn()
					.getResponse()
					.getContentAsString(), JsonNode.class);

		List<Covid> actualResult = om.convertValue(top5, new TypeReference<List<Covid>>() {	});

		Assertions.assertEquals(670, actualResult.get(0).getActive());
	}

	@Test
	public void givenEmployees_whenGetTotalBy_thenStatus200() throws Exception {
		mvc.perform(get("/api/covid/total?by=recovered").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", is(177)));
	}

	@Test
	public void givenEmployee_whenCreateEmployee_thenStatus201() throws Exception {
		Covid covid = Covid.builder().country("country8").death(10).active(100).recovered(1000).build();

		mvc.perform(post("/api/covid").contentType("application/json").content(om.writeValueAsString(covid)))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$", is("Created")));
	}
 }
