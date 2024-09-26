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
        // Verifica se já existe um planeta com o mesmo nome
        if (planet == null) {
            throw new IllegalArgumentException("O planeta não pode ser nulo");
        }

        if (planet.getName() == null || planet.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome do planeta não pode ser nulo ou vazio");
        }

        // Verifica se o planeta já existe no repositório
        if (planetRepository.existsByName(planet.getName())) {
            throw new IllegalArgumentException("Já existe um planeta com o nome " + planet.getName());
        }

        // Salva o novo planeta no repositório
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