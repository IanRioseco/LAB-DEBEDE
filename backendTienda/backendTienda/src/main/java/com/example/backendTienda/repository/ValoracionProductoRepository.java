package com.example.backendTienda.repository;

import com.example.backendTienda.entity.ValoracionProducto;
import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ValoracionProductoRepository extends JpaRepository<ValoracionProducto, Long> {
    Optional<ValoracionProducto> findByProductoAndUsuario(Producto producto, Usuario usuario);
    List<ValoracionProducto> findByProducto_IdProducto(Integer idProducto);
}
