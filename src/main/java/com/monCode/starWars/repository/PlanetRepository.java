package com.monCode.starWars.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.monCode.starWars.model.Planet;

@RepositoryRestResource(collectionResourceRel = "planets", path = "planets")
public interface PlanetRepository extends MongoRepository<Planet, String>{

	List<Planet> findByName(@Param("name") String name);
}
