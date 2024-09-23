package com.example.demo.service;

import com.example.demo.model.Planet;

import static com.example.demo.common.PlanetConstains.PLANET;
import static org.assertj.core.api.Assertions.assertThat; // Importação correta do assertThat do AssertJ

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = PlanetService.class)
public class PlanetServiceTest {

    @Autowired
    private PlanetService planetService;

    ///**
    // * Tests if creating a planet with valid data returns the expected Planet object.
    // */ Testa se criar um planeta com dados válidos retorna o planeta esperado
    @Test
    public void createdPlanet_withValidData_returnsPlanet() {
        Planet sut = planetService.createPlanet(PLANET);

        // Assert
        // Verifica se o resultado é o esperado
        assertThat(sut).isEqualTo(PLANET);
    }
}
