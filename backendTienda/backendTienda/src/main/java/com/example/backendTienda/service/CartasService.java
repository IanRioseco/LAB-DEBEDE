package com.example.backendTienda.service;

import com.example.backendTienda.entity.CartaColeccionable;
import com.example.backendTienda.repository.CartasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartasService {
    @Autowired
    private CartasRepository cartasRepository;

    public CartaColeccionable guardar(CartaColeccionable carta) {
        return cartasRepository.save(carta);
    }

    public List<CartaColeccionable> listarTodas() {
        return cartasRepository.findAll();
    }
}
