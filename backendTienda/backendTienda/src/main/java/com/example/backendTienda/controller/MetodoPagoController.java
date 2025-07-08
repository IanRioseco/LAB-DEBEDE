package com.example.backendTienda.controller;

import com.example.backendTienda.entity.MetodoPago;
import com.example.backendTienda.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/metodos-pago")
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService metodoPagoService;

    @PostMapping
    public ResponseEntity<MetodoPago> crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        return ResponseEntity.ok(metodoPagoService.crearMetodoPago(metodoPago));
    }

    @GetMapping
    public List<MetodoPago> listarMetodosPago() {
        return metodoPagoService.listarMetodosPago();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerPorId(@PathVariable Long id) {
        return metodoPagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable Long id) {
        metodoPagoService.eliminarMetodoPago(id);
        return ResponseEntity.noContent().build();
    }
}
