package com.qa.springdrinksproject.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.springdrinksproject.domain.Drink;
import com.qa.springdrinksproject.service.DrinkService;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DrinkControllerTest {
	@Autowired
	private MockMvc mvc;	
	@Autowired
	private ObjectMapper mapper;
	@MockBean
	private DrinkService service;
	
	@Test
	public void createTest() throws Exception {
		Drink input = new Drink("Beer", 600, true);
		String inputJSON = this.mapper.writeValueAsString(input);
		
		Mockito.when(this.service.create(input)).thenReturn(input);
		
		mvc.perform(post("/drink/create").contentType(MediaType.APPLICATION_JSON)
		.content(inputJSON))
		.andExpect(status().isCreated())
		.andExpect(content().json(inputJSON));
	}
	
	@Test
	public void readAllTest() throws Exception {
		mvc.perform(get("/drink/readAll"))
		.andExpect(status().isOk())
		.andExpect(content().json("[]"));		
	}
	
}
