package com.example.demo.service;

import com.example.demo.model.Planet;
import com.example.demo.repository.PlanetRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {

    @Mock
    private PlanetRepository planetRepository;

    @InjectMocks
    private PlanetService planetService;

    @BeforeEach
    void setUp() {
        // Inicializa os mocks antes de cada teste
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void shouldThrowExceptionWhenPlanetIsNull() {
        // Verifica se uma exceção é lançada quando o planeta é nulo
        assertThatThrownBy(() -> planetService.createPlanet(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("O planeta não pode ser nulo");

        // Verifica se o método save nunca foi chamado
        verify(planetRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenPlanetNameIsNull() {
        // Criação do objeto Planet com nome nulo
        Planet planet = new Planet();
        planet.setName(null);

        // Verifica se uma exceção é lançada quando o nome do planeta é nulo
        assertThatThrownBy(() -> planetService.createPlanet(planet))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("O nome do planeta não pode ser nulo ou vazio");

        // Verifica se o método save nunca foi chamado
        verify(planetRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenPlanetNameIsEmpty() {
        // Criação do objeto Planet com nome vazio
        Planet planet = new Planet();
        planet.setName("");

        // Verifica se uma exceção é lançada quando o nome do planeta é vazio
        assertThatThrownBy(() -> planetService.createPlanet(planet))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("O nome do planeta não pode ser nulo ou vazio");

        // Verifica se o método save nunca foi chamado
        verify(planetRepository, never()).save(any());
    }
}
