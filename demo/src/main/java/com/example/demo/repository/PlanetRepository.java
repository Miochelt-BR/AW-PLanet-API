package com.example.demo.repository;

import com.example.demo.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    // Método para verificar se um planeta com o nome fornecido já existe
    boolean existsByName(String name);
    // Métodos do JpaRepository são automaticamente implementados
}