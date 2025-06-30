package com.example.backendTienda.controller;

import com.example.backendTienda.DTO.ProductoCarroDTO;
import com.example.backendTienda.DTO.ProductoCarroRespuestaDTO;
import com.example.backendTienda.entity.ProductoCarro;
import com.example.backendTienda.entity.ProductoCarroId;
import com.example.backendTienda.entity.Producto;
import com.example.backendTienda.entity.Carro;
import com.example.backendTienda.service.ProductoCarroService;
import com.example.backendTienda.repository.ProductoRepository;
import com.example.backendTienda.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/producto-carro")
public class ProductoCarroController {
    @Autowired
    private ProductoCarroService productoCarroService;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CarroRepository carroRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProductoCarroDTO dto) {
        Optional<Producto> productoOpt = productoRepository.findById(dto.getIdProducto());
        Optional<Carro> carroOpt = carroRepository.findById(dto.getIdCarro());
        if (productoOpt.isEmpty() || carroOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Producto o Carro no encontrado");
        }
        ProductoCarroId id = new ProductoCarroId();
        id.setIdProducto(dto.getIdProducto());
        id.setIdCarro(dto.getIdCarro());
        ProductoCarro pc = new ProductoCarro();
        pc.setId(id);
        pc.setProducto(productoOpt.get());
        pc.setCarro(carroOpt.get());
        pc.setCantidadProducto(dto.getCantidadProducto());
        ProductoCarro guardado = productoCarroService.save(pc);

        // DTO de respuesta para evitar bucles
        ProductoCarroRespuestaDTO resp = new ProductoCarroRespuestaDTO();
        resp.setIdProducto(guardado.getProducto().getIdProducto());
        resp.setIdCarro(guardado.getCarro().getIdCarro());
        resp.setCantidadProducto(guardado.getCantidadProducto());
        resp.setPrecioUnitario(guardado.getProducto().getPrecioUnitario());
        resp.setSubtotal(guardado.getProducto().getPrecioUnitario() * guardado.getCantidadProducto());
        resp.setTotalCarro(guardado.getCarro().getPrecioTotal());
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/carro/{idCarro}")
    public List<ProductoCarro> getByCarro(@PathVariable String idCarro) {
        return productoCarroService.findByCarro(Integer.valueOf(idCarro));
    }

    @GetMapping("/producto/{idProducto}")
    public List<ProductoCarro> getByProducto(@PathVariable String idProducto) {
        return productoCarroService.findByProducto(Integer.valueOf(idProducto));
    }

    @GetMapping("/{idProducto}/{idCarro}")
    public ResponseEntity<ProductoCarro> getById(@PathVariable String idProducto, @PathVariable String idCarro) {
        ProductoCarroId id = new ProductoCarroId();
        id.setIdProducto(Integer.valueOf(idProducto));
        id.setIdCarro(Integer.valueOf(idCarro));
        Optional<ProductoCarro> pc = productoCarroService.findById(id);
        return pc.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idProducto}/{idCarro}")
    public ResponseEntity<Void> delete(@PathVariable String idProducto, @PathVariable String idCarro) {
        ProductoCarroId id = new ProductoCarroId();
        id.setIdProducto(Integer.valueOf(idProducto));
        id.setIdCarro(Integer.valueOf(idCarro));
        productoCarroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
