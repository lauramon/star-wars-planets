package com.monCode.starWars;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.monCode.starWars.model.Planet;

public class TestPlanetsUtils {
	
	private static ObjectMapper objectMapper = new ObjectMapper();


	public static String createNewPlanet(MockMvc mockMvc, String name, String terrain, String climate, Integer filmsNumber) throws JsonProcessingException, Exception {
		 
		Planet planet = new Planet(name, terrain, climate, filmsNumber);
		
		String reqStr = objectMapper.writeValueAsString(planet);
		
		MvcResult mvcResult = mockMvc.perform(post("/planets").accept(new MediaType("application", "hal+json")).content(reqStr))
				.andExpect(status().isCreated())
				.andReturn();
					
		String json = mvcResult.getResponse().getContentAsString();
		
		String idPath = JsonPath.read(json, "$._links.self.href");
				
		return idPath.substring("http://localhost/planets/".length(), idPath.length());
		
	}
	

}
