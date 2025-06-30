package com.example.backendTienda.repository;

import com.example.backendTienda.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Integer> {
}