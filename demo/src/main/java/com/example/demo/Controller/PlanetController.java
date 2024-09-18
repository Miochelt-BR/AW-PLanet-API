package com.example.demo.Controller;

import com.example.demo.model.Planet;
import com.example.demo.service.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planets")
public class PlanetController {

    private final PlanetService planetService;

    @Autowired
    public PlanetController(PlanetService planetService) {
        this.planetService = planetService;
    }

    // 1. Criar um planeta
    @PostMapping
    public ResponseEntity<Planet> createPlanet(@RequestBody Planet planet) {
        Planet planetCreated = planetService.createPlanet(planet);
        return ResponseEntity.status(HttpStatus.CREATED).body(planetCreated);
    }

    // 2. Obter todos os planetas
    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> planets = planetService.getAllPlanets();
        return ResponseEntity.ok(planets);
    }

    // 3. Obter um planeta específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Planet> getPlanet(@PathVariable Long id) {
        Planet planet = planetService.getPlanet(id);
        if (planet != null) {
            return ResponseEntity.ok(planet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Atualizar um planeta
    @PutMapping("/{id}")
    public ResponseEntity<Planet> updatePlanet(@PathVariable Long id, @RequestBody Planet planet) {
      var updatedPlanet = planetService.updatePlanet(id, planet);
        if (updatedPlanet != null) {
            return ResponseEntity.ok(updatedPlanet);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Deletar um planeta
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlanet(@PathVariable Long id) {
        boolean deleted = planetService.deletePlanet(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}