package com.example.backendTienda.controller;

import com.example.backendTienda.DTO.TiendaDTO;
import com.example.backendTienda.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    @PostMapping("/")
    public ResponseEntity<TiendaDTO> crearTienda(@RequestBody TiendaDTO tiendaDTO) {
        // Validar que se envíe el idJefe y nombreTienda
        if (tiendaDTO.getIdJefe() == null || tiendaDTO.getNombreTienda() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        // Crear la tienda y luego obtener el DTO completo
        var tienda = tiendaService.crearTienda(tiendaDTO);
        var tiendaCreadaDTO = tiendaService.obtenerTiendaConJefe(tienda.getIdTienda());
        return ResponseEntity.ok(tiendaCreadaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TiendaDTO> obtenerTiendaConJefe(@PathVariable Long id) {
        // Validación explícita del ID antes de llamar al servicio
        if (id == null) {
            return ResponseEntity.badRequest().body(null); // Respuesta clara en caso de error
        }

        TiendaDTO tiendaDTO = tiendaService.obtenerTiendaConJefe(id);
        return ResponseEntity.ok(tiendaDTO);
    }

    // ...existing code...
}