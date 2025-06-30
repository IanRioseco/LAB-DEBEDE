package com.example.backendTienda.service;

import com.example.backendTienda.entity.ProductoCarro;
import com.example.backendTienda.entity.ProductoCarroId;
import com.example.backendTienda.entity.Carro;
import com.example.backendTienda.repository.ProductoCarroRepository;
import com.example.backendTienda.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoCarroService {
    @Autowired
    private ProductoCarroRepository productoCarroRepository;
    @Autowired
    private CarroRepository carroRepository;

    public ProductoCarro save(ProductoCarro productoCarro) {
        // Guardar ProductoCarro
        ProductoCarro guardado = productoCarroRepository.save(productoCarro);
        // Actualizar el total del carro
        Carro carro = guardado.getCarro();
        if (carro != null && guardado.getProducto() != null && guardado.getCantidadProducto() != null) {
            Integer precioProducto = guardado.getProducto().getPrecioUnitario();
            Integer cantidad = guardado.getCantidadProducto();
            if (precioProducto != null && cantidad != null) {
                int subtotal = precioProducto * cantidad;
                if (carro.getPrecioTotal() == null) {
                    carro.setPrecioTotal(subtotal);
                } else {
                    carro.setPrecioTotal(carro.getPrecioTotal() + subtotal);
                }
                carroRepository.save(carro);
            }
        }
        return guardado;
    }

    public List<ProductoCarro> findByCarro(Integer idCarro) {
        return productoCarroRepository.findByCarro_IdCarro(idCarro);
    }

    public List<ProductoCarro> findByProducto(Integer idProducto) {
        return productoCarroRepository.findByProducto_IdProducto(idProducto);
    }

    public Optional<ProductoCarro> findById(ProductoCarroId id) {
        return productoCarroRepository.findById(id);
    }

    public void deleteById(ProductoCarroId id) {
        productoCarroRepository.deleteById(id);
    }
}
