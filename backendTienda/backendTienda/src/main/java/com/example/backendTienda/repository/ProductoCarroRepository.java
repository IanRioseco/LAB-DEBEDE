
package com.example.backendTienda.repository;

import com.example.backendTienda.entity.ProductoCarro;
import com.example.backendTienda.entity.ProductoCarroId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductoCarroRepository extends JpaRepository<ProductoCarro, ProductoCarroId> {
    List<ProductoCarro> findByCarro_IdCarro(Integer idCarro);
    List<ProductoCarro> findByProducto_IdProducto(Integer idProducto);

    // Ranking de productos por cantidad total vendida
    // Devuelve idProducto, suma de cantidad_producto, ordenado descendente
    @Query("SELECT pc.producto.idProducto as idProducto, pc.producto.nombreProducto as nombreProducto, SUM(pc.cantidadProducto) as totalVendido FROM ProductoCarro pc GROUP BY pc.producto.idProducto, pc.producto.nombreProducto ORDER BY totalVendido DESC")
    List<Object[]> rankingProductosMasVendidos();
}
