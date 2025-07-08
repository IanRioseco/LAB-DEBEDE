

package com.example.backendTienda.controller;

import com.example.backendTienda.entity.Compra;
import com.example.backendTienda.entity.Carro;
import com.example.backendTienda.service.CompraService;
import com.example.backendTienda.repository.CarroRepository;
import com.example.backendTienda.DTO.CompraRespuestaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private com.example.backendTienda.repository.ProductoCarroRepository productoCarroRepository;


    // Endpoint para mostrar nombres de productos comprados por usuarios según ubicación
    @GetMapping("/productos-por-ubicacion")
    public ResponseEntity<?> productosCompradosPorUbicacion(@RequestParam(required = false) String ubicacion) {
        if (ubicacion == null || ubicacion.isBlank()) {
            return ResponseEntity.badRequest().body("Debe proporcionar el parámetro 'ubicacion' en la URL");
        }
        List<Compra> compras = compraService.listarTodas().stream()
            .filter(c -> c.getCarro() != null && c.getCarro().getUsuario() != null && ubicacion.equalsIgnoreCase(c.getCarro().getUsuario().getUbicacion()))
            .toList();
        java.util.Set<String> nombresProductos = new java.util.HashSet<>();
        for (Compra compra : compras) {
            if (compra.getCarro() != null && compra.getCarro().getProductosCarro() != null) {
                for (var pc : compra.getCarro().getProductosCarro()) {
                    if (pc.getProducto() != null) {
                        nombresProductos.add(pc.getProducto().getNombreProducto());
                    }
                }
            }
        }
        return ResponseEntity.ok(nombresProductos);
    }


    @PostMapping("/{idCarro}")
    public ResponseEntity<CompraRespuestaDTO> crearCompra(@PathVariable Integer idCarro) {
        Carro carro = carroRepository.findById(idCarro).orElse(null);
        if (carro == null) {
            return ResponseEntity.badRequest().body(new CompraRespuestaDTO("El carro no existe"));
        }
        if (Boolean.TRUE.equals(carro.getEstado())) {
            return ResponseEntity.badRequest().body(new CompraRespuestaDTO("El carro ya fue comprado"));
        }
        // Obtener productos del carro
        var productosCarro = productoCarroRepository.findByCarro_IdCarro(idCarro);
        if (productosCarro.isEmpty()) {
            return ResponseEntity.badRequest().body(new CompraRespuestaDTO("El carro no tiene productos"));
        }
        // Validar stock de todos los productos antes de procesar la compra
        for (var pc : productosCarro) {
            var producto = pc.getProducto();
            if (producto.getStock() == null || producto.getStock() < pc.getCantidadProducto()) {
                return ResponseEntity.badRequest().body(new CompraRespuestaDTO("No hay stock suficiente para el producto: " + producto.getNombreProducto()));
            }
        }
        // Construir detalle y calcular total
        StringBuilder detalle = new StringBuilder();
        int total = 0;
        for (var pc : productosCarro) {
            var producto = pc.getProducto();
            int subtotal = producto.getPrecioUnitario() * pc.getCantidadProducto();
            detalle.append(producto.getNombreProducto())
                   .append(" x")
                   .append(pc.getCantidadProducto())
                   .append(" ($")
                   .append(producto.getPrecioUnitario())
                   .append(") | ");
            total += subtotal;
            // Descontar stock
            producto.setStock(producto.getStock() - pc.getCantidadProducto());
        }
        if (detalle.length() > 3) detalle.setLength(detalle.length() - 3); // quitar último separador
        // Marcar el carro como comprado
        carro.setEstado(true);
        carroRepository.save(carro);
        // Guardar la compra
        Compra compra = new Compra();
        compra.setCarro(carro);
        compra.setDetalle(detalle.toString());
        compra.setFecha(LocalDateTime.now());
        compra.setTotal(total);
        compraService.guardar(compra);
        return ResponseEntity.ok(new CompraRespuestaDTO(
            detalle.toString(),
            compra.getFecha().toString(),
            total
        ));
    }

    @GetMapping("/listar")
    public List<Compra> listarCompras() {
        return compraService.listarTodas();
    }
}
