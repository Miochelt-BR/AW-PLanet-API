package com.example.demo.service;

import com.example.demo.model.Planet;
import com.example.demo.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService {

    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) {
        this.planetRepository = planetRepository;
    }

    public Planet createPlanet(Planet planet) {
        if (planetRepository.existsByName(planet.getName())) {
            throw new IllegalArgumentException("Já existe um planeta com o nome " + planet.getName());
        }
        return planetRepository.save(planet);
    }

    public List<Planet> getAllPlanets() {
        return planetRepository.findAll();
    }

    public Planet getPlanet(Long id) {
        Optional<Planet> planet = planetRepository.findById(id);
        return planet.orElse(null);
    }

    public Planet updatePlanet(Long id, Planet planet) {
        Optional<Planet> existingPlanet = planetRepository.findById(id);
        if (existingPlanet.isPresent()) {
            planet.setId(id);
            return planetRepository.save(planet);
        }
        return null;
    }

    public boolean deletePlanet(Long id) {
        if (planetRepository.existsById(id)) {
            planetRepository.deleteById(id);
            return true;
        }
        return false;
    }
}