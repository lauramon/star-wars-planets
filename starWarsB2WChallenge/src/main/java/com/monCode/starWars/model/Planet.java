package com.monCode.starWars.model;

import org.springframework.data.annotation.Id;

public class Planet {
	
	@Id
	private String id;
	
	private String name;
	
	private String terrain;
	
	private String climate;
	
	private Integer	filmsNumber;
	
	protected Planet() {
		
	}

	public Planet(String name, String terrain, String climate, Integer filmsNumber) {
		
		this.name = name;
		this.terrain = terrain;
		this.climate = climate;
		this.filmsNumber = filmsNumber;
		
	}
	
	public String getId() {
		return id;
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


	public Integer getFilmsNumber() {
		return filmsNumber;
	}


	public void setFilmsNumber(Integer filmsNumber) {
		this.filmsNumber = filmsNumber;
	}
	
	public String toString() {
		return "Planet {id='" + id + '\'' +
					    ",name='" + name + '\'' +
						", climate='" + climate + '\'' +
						", terrain='" + terrain + '\'' +
						", filmsNumber= '" + filmsNumber + "' }" ;
	}

}




