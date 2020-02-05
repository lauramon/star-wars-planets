package com.monCode.starWars.client;

import java.util.Arrays;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SWPlanetClient {

	public SWPlanetClient() {
		
	}
	
	public SWPlanet getPlanet(String name) {
		//TODO: do not work properly because the films is linkedHashMap and I can not cast this type...
		System.setProperty("http.proxyHost", "proxy");
		System.setProperty("http.proxyPort", "80");
		
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        		
 		ResponseEntity<SWPlanet> planet = restTemplate.exchange("https://swapi.co/api/planets?search="+name,HttpMethod.GET,entity, SWPlanet.class);
		
		return planet.getBody();

	}
	
	public Integer getFilmsInPlanet(String name) {
		SWPlanet planet = getPlanet(name);
		
		return planet.getFilms().size();
		
	}

}
