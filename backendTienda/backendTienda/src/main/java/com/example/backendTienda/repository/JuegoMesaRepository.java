package com.example.backendTienda.repository;

import com.example.backendTienda.entity.JuegoMesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoMesaRepository extends JpaRepository<JuegoMesa, Integer> {
}
