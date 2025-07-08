package com.example.backendTienda.controller;

import com.example.backendTienda.entity.CartaColeccionable;
import com.example.backendTienda.service.CartasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cartas")
public class CartasController {
    @Autowired
    private CartasService cartasService;

    @PostMapping
    public ResponseEntity<CartaColeccionable> crearCarta(@RequestBody CartaColeccionable carta) {
        // Asignar tipoProducto automáticamente
        carta.setTipoProducto("Carta");
        // No asignar categoría ni tienda aquí
        CartaColeccionable guardada = cartasService.guardar(carta);
        return ResponseEntity.ok(guardada);
    }

    @GetMapping("/listar")
    public List<CartaColeccionable> listarCartas() {
        return cartasService.listarTodas();
    }
}
