package com.example.backendTienda.repository;

import com.example.backendTienda.entity.Tienda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiendaRepository extends JpaRepository<Tienda, Long> {
}
