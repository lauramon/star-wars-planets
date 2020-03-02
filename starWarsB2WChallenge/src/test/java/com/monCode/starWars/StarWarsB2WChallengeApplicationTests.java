package com.monCode.starWars;

import static com.monCode.starWars.TestPlanetsUtils.createNewPlanet;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Import(StarWarsB2WChallengeApplication.class)	
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) //Clear DB after every method
class StarWarsB2WChallengeApplicationTests {
	
	private final MediaType JSON_HAL = new MediaType("application", "hal+json");
	
	@Autowired
	private MockMvc mockMvc;		
	
	@Test
	public void testCreatePlanet() throws Exception {
	    
		createNewPlanet(mockMvc,"Jakku", "sand", "tropical", 4);
					
	}
	
	@Test
	public void testFindAllPlanets() throws Exception {
		//Size should be 0
		mockMvc.perform(get("/planets")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.page.totalElements", is(0)));
		
		createNewPlanet(mockMvc,"Bespin", "sand", "tropical", 4);
		createNewPlanet(mockMvc,"Marte", "aqua", "storm", 7);
		
		//Size should be 2
		mockMvc.perform(get("/planets")).andDo(print()).andExpect(status().isOk())
						.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.page.totalElements", is(2)));
	}


	@Test
	public void testFindByName() throws Exception {
		
		createNewPlanet(mockMvc,"Marte", "aqua", "storm", 7);
		
		mockMvc.perform(get("/planets/search/findByName?name=Marte")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$._embedded.planets[0].name", is("Marte")));
		
	}
	
	@Test
	public void testFindById() throws Exception {
		
		String id = createNewPlanet(mockMvc,"Marte", "aqua", "storm", 7);
		
		mockMvc.perform(get("/planets/" + id)).andDo(print()).andExpect(status().isOk())
			.andExpect(content().contentType(JSON_HAL)).andExpect(jsonPath("$.name", is("Marte")));
		
	}
	
	@Test
	public void testRemovePlanetById() throws Exception {
		
		String id = createNewPlanet(mockMvc,"Marte", "aqua", "storm", 7);
		
		//expected http status 204 no content after delete 
		mockMvc.perform(delete("/planets/" + id)).andDo(print()).andExpect(status().is(204));

		//if I find by id, I expect http status 404 not found
		mockMvc.perform(get("/planets/" + id)).andDo(print()).andExpect(status().is(404));

	}

	
}
