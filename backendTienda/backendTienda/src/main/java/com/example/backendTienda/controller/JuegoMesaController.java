package com.example.backendTienda.controller;

import com.example.backendTienda.entity.JuegoMesa;
import com.example.backendTienda.service.JuegoMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegoMesaController {
    @Autowired
    private JuegoMesaService juegoMesaService;

    @PostMapping
    public ResponseEntity<JuegoMesa> crearJuego(@RequestBody JuegoMesa juego) {
        // Asignar tipoProducto automáticamente
        juego.setTipoProducto("JuegoMesa");
        // La categoría puede asignarse aquí si se recibe, o dejarse para otro endpoint
        JuegoMesa guardado = juegoMesaService.guardar(juego);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    public List<JuegoMesa> listarJuegos() {
        return juegoMesaService.listarTodos();
    }
}
