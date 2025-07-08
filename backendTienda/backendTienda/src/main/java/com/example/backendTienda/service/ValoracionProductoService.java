package com.example.backendTienda.service;

import com.example.backendTienda.entity.ValoracionProducto;
import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Usuario;
import com.example.backendTienda.repository.ValoracionProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ValoracionProductoService {
    @Autowired
    private ValoracionProductoRepository valoracionProductoRepository;

    public ValoracionProducto guardar(ValoracionProducto valoracion) {
        return valoracionProductoRepository.save(valoracion);
    }

    public Optional<ValoracionProducto> buscarPorProductoYUsuario(Producto producto, Usuario usuario) {
        return valoracionProductoRepository.findByProductoAndUsuario(producto, usuario);
    }

    public List<ValoracionProducto> listarPorProducto(Integer idProducto) {
        return valoracionProductoRepository.findByProducto_IdProducto(idProducto);
    }
}
