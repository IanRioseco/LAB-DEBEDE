package com.example.backendTienda.repository;

import com.example.backendTienda.entity.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Long> {
    List<ListaDeseos> findByUsuario_Id(Long idUsuario);

    // Ranking de productos más deseados (más veces agregados a listas de deseos)
    @org.springframework.data.jpa.repository.Query("SELECT p.idProducto, p.nombreProducto, COUNT(ldp) as totalDeseado FROM ListaDeseos l JOIN l.productos p JOIN l.productos ldp GROUP BY p.idProducto, p.nombreProducto ORDER BY totalDeseado DESC")
    java.util.List<Object[]> rankingProductosMasDeseados();
}
