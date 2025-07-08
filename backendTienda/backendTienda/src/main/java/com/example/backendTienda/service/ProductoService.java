package com.example.backendTienda.service;

import com.example.backendTienda.DTO.RankingDTO;
import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Tienda;
import com.example.backendTienda.repository.ProductoRepository;
import com.example.backendTienda.repository.TiendaRepository;
import com.example.backendTienda.repository.ProductoCarroRepository;
import com.example.backendTienda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {


    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private ProductoCarroRepository productoCarroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public java.util.Optional<com.example.backendTienda.entity.Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    // ...existing code...


    // Filtrar productos por ubicación geográfica del cliente
    public List<Producto> filtrarPorUbicacion(String ubicacion) {
        return productoRepository.findAll().stream()
                .filter(p -> p.getDireccionComercial() != null && p.getDireccionComercial().equalsIgnoreCase(ubicacion))
                .toList();
    }

    public List<RankingDTO> rankingProductosMasVendidos() {
        List<Object[]> resultados = productoCarroRepository.rankingProductosMasVendidos();
        List<RankingDTO> ranking = new java.util.ArrayList<>();
        for (Object[] fila : resultados) {
            Integer idProducto = (Integer) fila[0];
            String nombreProducto = (String) fila[1];
            Long totalVendido = (Long) fila[2];
            ranking.add(new RankingDTO(idProducto, nombreProducto, totalVendido));
        }
        return ranking;
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> getProductoById(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void deleteProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto asociarProductoATienda(Integer idProducto, Long idTienda) {
        Producto producto = productoRepository.findById(idProducto)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Tienda tienda = tiendaRepository.findById(idTienda)
            .orElseThrow(() -> new RuntimeException("Tienda no encontrada"));
        if (producto.getTiendas() == null) {
            producto.setTiendas(new java.util.HashSet<>());
        }
        producto.getTiendas().add(tienda);
        return productoRepository.save(producto);
    }
}
