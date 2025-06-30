package com.example.backendTienda.service;

import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Tienda;
import com.example.backendTienda.repository.ProductoRepository;
import com.example.backendTienda.repository.TiendaRepository;
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
