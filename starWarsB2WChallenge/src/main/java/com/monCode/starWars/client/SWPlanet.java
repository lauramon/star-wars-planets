package com.monCode.starWars.client;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SWPlanet {

	private String name;
	private String terrain;
	private String climate;
	private List<String> films;

	public SWPlanet() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	@Override
	public String toString() {
		return "SWPlanet {name='" + name + '\'' +
						", climate='" + climate + '\'' +
						", terrain='" + terrain + '\'' +
						", films= [" + films + "] }" ;
	}
	
}
