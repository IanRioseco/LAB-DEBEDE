package com.example.backendTienda.repository;

import com.example.backendTienda.entity.ProductoCarro;
import com.example.backendTienda.entity.ProductoCarroId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoCarroRepository extends JpaRepository<ProductoCarro, ProductoCarroId> {
    List<ProductoCarro> findByCarro_IdCarro(Integer idCarro);
    List<ProductoCarro> findByProducto_IdProducto(Integer idProducto);
}
