package com.example.backendTienda.repository;

import com.example.backendTienda.entity.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Long> {
    List<ListaDeseos> findByUsuario_Id(Long idUsuario);
}
