package com.example.backendTienda.controller;

import com.example.backendTienda.entity.Carro;
import com.example.backendTienda.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    public List<Carro> getAllCarros() {
        return carroService.getAllCarros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarroById(@PathVariable Integer id) {
        return carroService.getCarroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Carro createCarro(@RequestBody Carro carro) {
        return carroService.saveCarro(carro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carro> updateCarro(@PathVariable Integer id, @Valid @RequestBody Carro carro, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build(); // Manejo de errores de validación
        }

        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().build(); // Validación del ID
        }

        Optional<Carro> existingCarro = carroService.getCarroById(id);
        if (!existingCarro.isPresent()) {
            return ResponseEntity.notFound().build(); // Carro no encontrado
        }

        if (carro.getIdCarro() != null && !carro.getIdCarro().equals(id)) {
            return ResponseEntity.badRequest().build(); // Conflicto en los IDs
        }

        carro.setIdCarro(id); // Asignación del ID al objeto carro
        return ResponseEntity.ok(carroService.saveCarro(carro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Integer id) {
        carroService.deleteCarro(id);
        return ResponseEntity.noContent().build();
    }
}